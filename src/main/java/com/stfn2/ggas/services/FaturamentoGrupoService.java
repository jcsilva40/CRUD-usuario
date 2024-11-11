package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoGrupoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoFilterDTO;
import com.stfn2.ggas.repositories.FaturamentoGrupoRepository;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaturamentoGrupoService extends BaseService<FaturamentoGrupo, FaturamentoGrupoDTO, FaturamentoGrupoBasicDTO, FaturamentoGrupoFilterDTO, FaturamentoGrupoRepository> {

   @Autowired
   private ParametroSistemaService parametroSistemaService;

   public boolean isPeriodicidadeGrupoMultiplosCiclos(FaturamentoGrupo grupo) throws NegocioException {
      if (grupo == null || grupo.getPeriodicidade() == null) {
         return false;
      }
      return parametroSistemaService.obterValorParametroUtilizacaoMultiplosCiclos() && grupo.getPeriodicidade().isMultiplusCiclos();
   }

   public List<FaturamentoGrupoVO> listaFaturamentoGrupo() {
      return repository.listaFaturamentoGrupo();
   }
}

