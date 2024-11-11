package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.FaturaHistorico;
import com.stfn2.ggas.domain.dtos.FaturaHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaHistoricoFilterDTO;
import com.stfn2.ggas.repositories.FaturaHistoricoRepository;
import com.stfn2.ggas.services.componentes.faturamento.vo.SubRelatorioHistoricoConsumoVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaHistoricoService extends BaseService<FaturaHistorico, FaturaHistoricoDTO, FaturaHistoricoBasicDTO, FaturaHistoricoFilterDTO, FaturaHistoricoRepository> {

   public List<SubRelatorioHistoricoConsumoVO> obterColecaoHistoricoFatura(Fatura fatura) {
      List<SubRelatorioHistoricoConsumoVO> colecaoHistoricosConsumo = new ArrayList<>();

      List<FaturaHistorico> historicoFatura = obterPorChaveFatura(fatura.getId());

      for (FaturaHistorico historico : historicoFatura) {
         SubRelatorioHistoricoConsumoVO subRelHistConsumo = new SubRelatorioHistoricoConsumoVO();

         subRelHistConsumo.setNotaFiscalFatura(String.valueOf(historico.getNotaFiscalFatura()));
         subRelHistConsumo.setVolumeMedido(historico.getVolumeMedido());
         subRelHistConsumo.setValor(historico.getValor());
         subRelHistConsumo.setVencimento(historico.getVencimento());
         subRelHistConsumo.setPagamento(historico.getPagamento());

         subRelHistConsumo.setTipo(fatura.getDocumentoTipo().getDescricao());
         subRelHistConsumo.setMesAno(historico.getAnoMes().toString());
         subRelHistConsumo.setDataLeitura(" ");

         colecaoHistoricosConsumo.add(subRelHistConsumo);
      }

      return colecaoHistoricosConsumo;
   }


   public List<FaturaHistorico> obterPorChaveFatura(Long faturaId) {
      List<FaturaHistorico> listaHistoricos = repository.obterPorChaveFatura(faturaId);
      return listaHistoricos;
   }

   /*public List<FaturaHistoricoGraficoDTO> graficoConsumo(FaturaHistoricoGraficoFilterDTO filter){

      List<FaturaHistoricoGraficoDTO> listaFaturaHistoricoGrafico = new ArrayList<>();
      for(Long idPontoConsumo : filter.getListaIdPontoConsumo()){
         PontoConsumo pontoConsumo = CpControlers.getControladorPontoConsumo().buscarPontoConsumo(idPontoConsumo);
         FaturaHistoricoGraficoDTO faturaHistoricoGrafico = CpControlers.getControladorCompagas().obterUltimasFaturasParaGrafico(
                 pontoConsumo, filter.getUltimosDozesFaturamentos(),	filter.getPeriodoInicial(), filter.getPeriodoFinal());
         listaFaturaHistoricoGrafico.add(faturaHistoricoGrafico);
      }
      return listaFaturaHistoricoGrafico;
   }*/
}