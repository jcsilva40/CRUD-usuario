package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.HistoricoLegado;
import com.stfn2.ggas.domain.dtos.HistoricoLegadoDTO;
import com.stfn2.ggas.domain.dtos.basic.HistoricoLegadoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.HistoricoLegadoFilterDTO;
import com.stfn2.ggas.repositories.HistoricoLegadoRepository;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioHistoricoConsumoVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoLegadoService extends BaseService<HistoricoLegado, HistoricoLegadoDTO, HistoricoLegadoBasicDTO, HistoricoLegadoFilterDTO, HistoricoLegadoRepository> {

   public List<SubRelatorioHistoricoConsumoVO> listarRelatorioHistoricoLegado(String idPontoConsumo){
      List<SubRelatorioHistoricoConsumoVO> relatoriohistoricosLegado = new ArrayList<>();

      List<HistoricoLegado> historicosLegado = listarHistoricoLegado(idPontoConsumo);
      for(HistoricoLegado hist : historicosLegado){
         SubRelatorioHistoricoConsumoVO srh = new SubRelatorioHistoricoConsumoVO();
         srh.setNotaFiscalFatura(hist.getNotaFiscal());
         srh.setVolumeMedido(hist.getVolumeMedido());
         srh.setValor(hist.getValor());
         srh.setVencimento(hist.getVencimento());
         srh.setPagamento(hist.getDataPagamento());

         relatoriohistoricosLegado.add(srh);
      }
      return relatoriohistoricosLegado;
   }

   public List<HistoricoLegado> listarHistoricoLegado(String pontoConsumoId) {
      List<HistoricoLegado> retorno = null;
         retorno = repository.listarHistoricoLegado(pontoConsumoId);
      if (retorno == null) {
         retorno = new ArrayList<HistoricoLegado>();
      }
      return retorno;
   }
}