package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.ParametroSistema;
import com.stfn2.ggas.domain.dtos.ParametroSistemaDTO;
import com.stfn2.ggas.domain.dtos.basic.ParametroSistemaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ParametroSistemaFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoParametroSistemaEnum;
import com.stfn2.ggas.repositories.ParametroSistemaRepository;
import com.stfn2.ggas.services.componentes.vo.VariavelVO;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ParametroSistemaService extends BaseService<ParametroSistema, ParametroSistemaDTO, ParametroSistemaBasicDTO, ParametroSistemaFilterDTO, ParametroSistemaRepository> {

   private HibernateTemplate hibernateTemplate;

   private Map<String, Object> cache = new HashMap<String, Object>();
   private Map<String, ParametroSistema> parametroCache = new HashMap<String, ParametroSistema>();
   private Log log = new Log(this.getClass());
   private static final int VALOR_INTEIRO_PARA_FALSO = 0;

   public Map<String, Object> getCache() {
      return cache;
   }

   public void setCache(Map<String, Object> cache) {
      this.cache = cache;
   }

   public boolean obterValorParametroUtilizacaoMultiplosCiclos()
           throws NegocioException {
      ParametroSistema  parametro =  obterParametroPorCodigo(Constantes.PARAMETRO_UTILIZAR_MULTIPLOS_CICLOS);

      if( parametro == null || !StringUtils.isNumeric(parametro.getValor())) {
         return false;
      }
      return Integer.parseInt(parametro.getValor()) != VALOR_INTEIRO_PARA_FALSO;
   }

   public ParametroSistema obterParametroPorCodigo(String codigo) {
      return obterParametroPorCodigo(codigo, true);
   }

   public ParametroSistema obterParametroPorCodigo(String codigo, boolean cacheable) {
      if(!parametroCache.containsKey(codigo)){
         ParametroSistema parametro = repository.obterParametroPorCodigo(codigo);
         if(parametro!=null){
            parametroCache.put(codigo, parametro);
         }
      }
      return parametroCache.get(codigo);
   }

   public Object obterValorDoParametroPorCodigo(String codigo, VariavelVO... variaveis) throws NegocioException {

      ParametroSistema parametro = null;
      Object retorno = null;

      if(cache.containsKey(codigo) && variaveis.length == 0) {
         log.info("### <<< CACHE >>> ### obterValorDoParametroPorCodigo: " + codigo);
         retorno = cache.get(codigo);
      } else {

         parametro = this.obterParametroPorCodigo(codigo);

         if(parametro != null) {
            if(parametro.getTipoParametroSistema() == TipoParametroSistemaEnum.ESTATICO) {
               retorno = parametro.getValor();
            } else if(parametro.getTipoParametroSistema() == TipoParametroSistemaEnum.DINAMICA) {
               retorno = hibernateTemplate.getSessionFactory().getCurrentSession()
                .get(parametro.getClasseEntidade(), Integer.valueOf(parametro.getValor()));
            } else if(parametro.getTipoParametroSistema() == TipoParametroSistemaEnum.NEGOCIO) {

               String logicaNegocio = parametro.getValor();
               Binding binding = new Binding();
               if((variaveis != null) && (variaveis.length > 0)) {
                  VariavelVO variavel = null;
                  for (int i = 0; i < variaveis.length; i++) {
                     variavel = variaveis[i];
                     binding.setVariable(variavel.getNome(), variavel.getObjeto());
                  }
               }

               try {
                  GroovyShell shell = new GroovyShell(binding);
                  shell.evaluate(logicaNegocio);
                  retorno = binding.getVariable("retornoParametro");
               } catch(Exception e) {
                  log.info("### Erro ao tentar obter o parametro: " + codigo + " : " + e.getMessage());
                  throw new NegocioException(Constantes.ERRO_PARAMETRO_NEGOCIO, codigo);
               }

            }

         } else {
            throw new NegocioException("ERRO_NEGOCIO_PARAMETRO_SISTEMA_NAO_ENCONTRADO", codigo);
         }
         log.info("### [[[ BANCO ]]] ###  obterValorDoParametroPorCodigo: " + codigo + " do banco");
         cache.put(codigo, retorno);
      }
      return retorno;

   }

   public List<Long> obterListaLongDeParametro(String parametro) {
      List<Long> lista = new CopyOnWriteArrayList<>();

      ParametroSistema itensDoParametro;
      try {
         itensDoParametro = obterParametroPorCodigo(parametro);
      } catch (Exception e) {
       log.erro("Erro ao tentar obter valores do Parâmetro: " + parametro, e.getMessage());
       return lista;
      }

      if (itensDoParametro == null || itensDoParametro.getValor() == null || itensDoParametro.getValor().isEmpty()) {
         log.erro("Constante: " + parametro + ", não contém valor.", null);
         return lista;
      }

      if (itensDoParametro.getValor().contains(";")) {
         for (String valor : itensDoParametro.getValor().split(";")) {
            lista.add(Long.parseLong(valor));
         }
      } else {
         lista.add(Long.parseLong(itensDoParametro.getValor()));
      }

      return lista;
   }
}