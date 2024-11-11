package com.stfn2.ggas.tools;

import com.stfn2.ggas.config.exceptions.types.GGASException;
import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.exception.SQLGrammarException;

import java.util.*;

public class ToolUtil {

   public static String getMsgExcecao(Exception e) {
      StringBuilder sb = new StringBuilder();
      sb.append("Exceção: ").append(e.getClass().getName()).append("\n");
      sb.append("Detalhes: ").append("\n");
      sb.append("Mensagem: ").append(e.getMessage()).append("\n");
      if (e.getCause() != null) {
         if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException violation = (ConstraintViolationException) e.getCause();
            if (violation.getCause() != null) {
               sb.append("Causa: ").append(violation.getCause().toString()).append("\n");
               return "Causa: " + violation.getCause().toString();
            } else {
               sb.append("Causa: ").append(e.getCause().toString()).append("\n");
            }
         } else {
            sb.append("Causa: ").append(e.getCause().toString()).append("\n");
         }
      }

      sb.append("Localização: ").append(e.getLocalizedMessage()).append("\n");

      if (e instanceof GGASException) {
         GGASException eg = (GGASException) e;
         sb.append("Chave Erro: ").append(eg.getChaveErro()).append("\n");
         if (eg instanceof NegocioException) {
            NegocioException negocioEx = (NegocioException) e;

            Map<String, Object> m = negocioEx.getErros();
            if (m != null) {
               sb.append("Outras Keys:{\n");
               for (String key : m.keySet()) {
                  Object ob = m.get(key);
                  String value = ob.toString();
                  sb.append(key).append(":").append(value).append(",\n");
               }
               sb.append("}\n");
            }
         }
      } else if (e instanceof SQLGrammarException) {
         SQLGrammarException sqlEx = (SQLGrammarException) e;
         sb.append("Cód Erro: ").append(sqlEx.getErrorCode()).append("\n");

      }
      System.out.println(sb.toString());
      return sb.toString();
   }

   public static <T> T primeiroElemento(Collection<T> colecao) {
      if (CollectionUtils.isNotEmpty(colecao)) {
         return (T) CollectionUtils.get(colecao, 0);
      }
      return null;
   }

   public static <T> T coalescenciaNula(T valorTeste, T valorCasoNulo) {
      if (valorTeste != null) {
         return valorTeste;
      } else {
         return valorCasoNulo;
      }
   }

   public static Long[] listParaArrayIds(List<?> entidades) {

      List<Long> novoArray = new ArrayList<Long>();
      Object entidade = null;
      for (Iterator<?> iterator = entidades.iterator(); iterator.hasNext();) {
         entidade = iterator.next();
         if (entidade instanceof BaseEntity) {
            novoArray.add(Long.valueOf(((BaseEntity) entidade).getId()));
         } else if(entidade instanceof Long){
            novoArray.add((Long)entidade);
         }
      }
      Long[] retorno = new Long[novoArray.size()];
      novoArray.toArray(retorno);

      return retorno;
   }

   public static <T> boolean isNullOrEmpty(List<T> collection) {
      return collection == null || collection.isEmpty() || "[null]".equals(collection.toString());
   }

   public static <K, T> T getElemento(Map<K, List<T>> mapa, K chave) {
      T elemento = null;
      if (mapa != null && !mapa.isEmpty()) {
         List<T> elementos = mapa.get(chave);
         if (!isNullOrEmpty(elementos)) {
            Iterator<T> iterator = elementos.iterator();
            elemento = iterator.next();
         }
      }
      return elemento;
   }

   public static <T extends BaseEntity> Map<String, T> trasformarParaMapaPorHashString(Collection<T> lista) {

      Map<String, T> mapa = new HashMap<String, T>();

      for (T elemento : lista) {
         String stringDataReferencia = elemento.hashString();
         mapa.put(stringDataReferencia, elemento);
      }

      return mapa;
   }
}
