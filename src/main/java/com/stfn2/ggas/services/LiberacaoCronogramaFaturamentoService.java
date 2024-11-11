package com.stfn2.ggas.services;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.DownloadDTO;
import com.stfn2.ggas.domain.dtos.LiberacaoCronogramaFaturamentoDTO;
import com.stfn2.ggas.domain.dtos.basic.LiberacaoCronogramaFaturamentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.LiberacaoCronogramaFaturamentoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import com.stfn2.ggas.repositories.*;
import com.stfn2.ggas.services.componentes.faturamento.EmitirRelatorioFaturaComponent;
import com.stfn2.ggas.services.componentes.relatorio.RelatorioComponent;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.ComponenteRelatorioPosFaturamento;
import com.stfn2.ggas.services.componentes.vo.FaturaLiberacaoVO;
import com.stfn2.ggas.services.componentes.vo.LiberacaoCronogramaVO;
import com.stfn2.ggas.services.componentes.vo.ValidacaoLiberacaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiberacaoCronogramaFaturamentoService extends BaseService<LiberacaoCronogramaFaturamento, LiberacaoCronogramaFaturamentoDTO, LiberacaoCronogramaFaturamentoBasicDTO,
        LiberacaoCronogramaFaturamentoFilterDTO, LiberacaoCronogramaFaturamentoRepository> {

   @Autowired
   private FaturaRepository faturaRepository;

   @Autowired
   private FaturamentoGrupoRepository faturaGrupoRepository;

   @Autowired
   private FaturamentoCronogramaRepository faturaCronogramaRepository;

   @Autowired
   private RotaRepository rotaRepository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ComponenteRelatorioPosFaturamento componenteRelatorioPosFaturamento;

   @Autowired
   private FaturaService faturaService;

   @Autowired
   private EmitirRelatorioFaturaComponent emitirRelatorioFaturaComponent;

   @Autowired
   private RotaService rotaService;


   public List<LiberacaoCronogramaVO> carregarLiberacoesCronograma(LiberacaoCronogramaFaturamentoFilterDTO filterDTO) {
      List<LiberacaoCronogramaVO> liberacoes = new ArrayList<>();
      List<LiberacaoCronogramaFaturamento> lista = this.filterEntity(filterDTO);
      List<String> etapas;

      for (LiberacaoCronogramaFaturamento lcf : lista) {
         boolean isExist = liberacoes.stream().anyMatch(obj -> compararListasLiberacao(obj, lcf));
                 //obj -> obj.getFaturamentoGrupo().equals(lcf.getFaturamentoGrupo().getId() ));
         if (isExist) {
            LiberacaoCronogramaVO lc = liberacoes.stream().filter(obj -> obj.getFaturamentoGrupo()
                    .equals(lcf.getFaturamentoGrupo().getId()) && obj.getAnoMes().equals(lcf.getAnoMes()) && obj.getCiclo().equals(lcf.getCiclo())).findFirst().get();
            LiberacaoCronogramaFaturamentoDTO lcTemp = new LiberacaoCronogramaFaturamentoDTO(lcf);
            lc.addEtapas(lcTemp);
         } else {
            LiberacaoCronogramaVO liberacao = new LiberacaoCronogramaVO();
            liberacao.setFaturamentoGrupo(lcf.getFaturamentoGrupo().getId());
            liberacao.setFaturamentoGrupoDescricao(lcf.getFaturamentoGrupo().getDescricao());
            FaturamentoCronograma cronograma = obterCronograma(lcf.getFaturamentoGrupo().getId(),
                    lcf.getAnoMes(), lcf.getCiclo());
            liberacao.setFaturamentoCronograma(cronograma.getId());
            liberacao.setAnoMes(lcf.getAnoMes());
            liberacao.setCiclo(lcf.getCiclo());
            LiberacaoCronogramaFaturamentoDTO lcTemp = new LiberacaoCronogramaFaturamentoDTO(lcf);
            liberacao.addEtapas(lcTemp);
            liberacoes.add(liberacao);
         }
      }
      for (LiberacaoCronogramaVO lc : liberacoes) {
         ValidacaoLiberacaoVO validacao = new ValidacaoLiberacaoVO();
         validacao.setAnoMes(lc.getAnoMes());
         validacao.setCiclo(lc.getCiclo());
         validacao.setId(lc.getFaturamentoGrupo());
         List<FaturaLiberacaoVO> faturas = verificarFaturasPorGrupoFaturamento(validacao);
         if (faturas.size() > 0) {
            lc.addFatura(faturas);
         }

         if (lc.getEtapas() != null) {
            Boolean isPendente =
                    lc.getEtapas().stream().anyMatch(etapa -> !etapa.getStatus());
            if (isPendente) {
               lc.setStatusCronograma("Validação Pendente");
            } else {
               lc.setStatusCronograma("Validação Concluida");
            }
         }
      }

      List<LiberacaoCronogramaVO> sortedByFaturamentoGrupo = liberacoes.stream()
              .sorted(Comparator.comparingLong(LiberacaoCronogramaVO::getFaturamentoGrupo))
              .collect(Collectors.toList());

      return sortedByFaturamentoGrupo;
   }

   public FaturamentoCronograma obterCronograma(Long id, Integer anoMes, Integer ciclo) {
      FaturamentoCronograma cronograma = faturaCronogramaRepository.obterCronograma(id, anoMes, ciclo);
      return cronograma;
   }

   public List<FaturaLiberacaoVO> verificarFaturasPorGrupoFaturamento(ValidacaoLiberacaoVO validacao) {

      List<Fatura> faturas = this.obterFaturasPorRota(validacao);
      List<FaturaLiberacaoVO> faturasLiberacao = new ArrayList<>();

      for (Fatura fatura : faturas) {
         FaturaLiberacaoVO f = new FaturaLiberacaoVO(fatura);
         faturasLiberacao.add(f);
      }

      return faturasLiberacao;
   }

   public static String removerAcentosEJuntar(String str) {
      String normalized = Normalizer.normalize(str, Normalizer.Form.NFD)
              .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

      String alphanumericOnly = normalized.replaceAll("[^a-zA-Z0-9]", " ");

      String result = alphanumericOnly.replaceAll("\\s+", "").toLowerCase();
      return result;
   }

   public void validarSegmento(ValidacaoLiberacaoVO dto) {
      FaturaFilterDTO faturaFilter = new FaturaFilterDTO();
      faturaFilter.setId(dto.getIdFatura());
      faturaFilter.setAnoMes(dto.getAnoMes());
      faturaFilter.setCiclo(dto.getCiclo());
      //faturaFilter.setSegmentoId(dto.getIdSegmento());
      faturaFilter.setStatusFaturaId(StatusFaturaEnum.VALIDACAO_PENDENTE);

      List<Rota> rotas = rotaRepository.obterRotasPeloFaturamentoGrupo(dto.getId());
      List<Fatura> faturasSegmento = new ArrayList<>();
      for (Rota rota : rotas) {
         faturaFilter.setRotaId(rota.getId());
         faturasSegmento.addAll(this.verificarFaturasPorStatus(faturaFilter));
      }

      for (Fatura faturaSeg : faturasSegmento) {
         this.liberarFatura(faturaSeg.getId());
      }
   }

   private List<Fatura> verificarFaturasPorStatus(FaturaFilterDTO faturaFilter) {
      List<Fatura> faturasPorStatus = new ArrayList<>();
      faturasPorStatus = faturaService.verificarFaturasPorStatus(faturaFilter);
     // faturasPorStatus = faturaRepository.filter(faturaFilter);
      return faturasPorStatus;
   }

   public void validarEtapa(Long idEtapa, Long idUsuario) {
      LiberacaoCronogramaFaturamento liberacao = this.repository.findById(idEtapa).get();
      if (idUsuario != null) {
         User usuario = userRepository.findById(idUsuario).get();
         liberacao.setUsuario(usuario);
      }
      liberacao.setStatus(true);
      repository.save(liberacao);

      checkLiberacao(liberacao);
   }

   private void checkLiberacao(LiberacaoCronogramaFaturamento liberacao) {
      ValidacaoLiberacaoVO dto = new ValidacaoLiberacaoVO();
      LiberacaoCronogramaFaturamentoFilterDTO filtro = new LiberacaoCronogramaFaturamentoFilterDTO();
      Boolean liberado = true;
      filtro.setAnoMes(liberacao.getAnoMes());
      filtro.setCiclo(liberacao.getCiclo());
      filtro.setFaturamentoGrupoId(liberacao.getFaturamentoGrupo().getId());
      filtro.setVerificacao(true);
      List<LiberacaoCronogramaVO> liberacoes = carregarLiberacoesCronograma(filtro);
      for (LiberacaoCronogramaFaturamentoDTO lib : liberacoes.get(0).getEtapas()) {
         if (lib.getStatus().equals(false)) {
            liberado = false;
         }
      }
      if (liberado) {
         if (liberacoes.get(0).getFaturas().size() > 0) {
            Long idSegmento = liberacoes.get(0).getFaturas().get(0).getSegmentoId();
            dto.setId(liberacao.getFaturamentoGrupo().getId());
            dto.setAnoMes(liberacao.getAnoMes());
            dto.setCiclo(liberacao.getCiclo());
            dto.setIdSegmento(idSegmento);
            validarSegmento(dto);
         }
      }
   }

   public void bloquearFatura(Long idFatura) {
      Fatura fatura = faturaRepository.getById(idFatura);
      fatura.setStatusFatura(StatusFaturaEnum.VALIDADA_BLOQUEADA);

      faturaRepository.save(fatura);
   }

   public void liberarFatura(Long idFatura) {
      Fatura fatura = faturaRepository.getById(idFatura);
      fatura.setStatusFatura(StatusFaturaEnum.VALIDADA_LIBERADA);

      faturaRepository.save(fatura);
   }

   public DownloadDTO downloadPlanilhaValidacao(LiberacaoCronogramaFaturamentoFilterDTO filter){

      FaturamentoGrupoFilterDTO faturamentoGrupoFilterDTO = new FaturamentoGrupoFilterDTO();
      faturamentoGrupoFilterDTO.setId(filter.getFaturamentoGrupoId());
      List<FaturamentoGrupo> faturamentoGrupo = faturaGrupoRepository.filter(faturamentoGrupoFilterDTO);
      String name =
              "Planilha-Faturamento-" + faturamentoGrupo.get(0).getDescricao() + "-" + filter.getAnoMes() + "-" + filter.getCiclo();

      byte[] planilha = componenteRelatorioPosFaturamento.processar(filter);
      DownloadDTO relatorioFaturamento = new DownloadDTO(name, "xlsx", planilha);

      return relatorioFaturamento;
   }

   public DownloadDTO obterFaturas(LiberacaoCronogramaFaturamentoFilterDTO filter){
      Fatura fatura = faturaRepository.getById(filter.getId());

      DownloadDTO faturaObtida =
              new DownloadDTO(fatura.getPontoConsumo().getDescricao() + fatura.getAnoMes() + "-" + fatura.getCiclo(), "pdf"
              , emitirRelatorioFaturaComponent.gerarImpressaoFaturaSegundaVia(fatura.getId()));

      return faturaObtida;
   }

   public DownloadDTO downloadFaturasPorGrupoFaturamento(ValidacaoLiberacaoVO validacao) {

      List<Fatura> faturas = this.obterFaturasPorRota(validacao);
      List<byte[]> retornoRelatorioAgrupado = new ArrayList<byte[]>();

      for(Fatura fatura : faturas){
         retornoRelatorioAgrupado.add(emitirRelatorioFaturaComponent.gerarImpressaoFaturaSegundaVia(fatura.getId()));
      }

      byte[] faturasByte = RelatorioComponent.unificarRelatoriosPdf(retornoRelatorioAgrupado);

      DownloadDTO faturaGrupoFaturamento = new DownloadDTO("Todas_Faturas_Segmento", "pdf", faturasByte);

      return faturaGrupoFaturamento;
   }

   private List<Fatura> obterFaturasPorRota(ValidacaoLiberacaoVO validacao) {
      List<Fatura> faturas = new ArrayList<>();
      List<Rota> rotas = rotaRepository.obterRotasPeloFaturamentoGrupo(validacao.getId());
      for (Rota rota : rotas) {
         faturas.addAll(faturaRepository.obterFaturasPorAnoMesCicloRota(validacao.getAnoMes(), validacao.getCiclo(),rota.getId()));
      }

      return faturas;
   }

   private Boolean compararListasLiberacao(LiberacaoCronogramaVO objVO,
                                           LiberacaoCronogramaFaturamento obj){
      return objVO.getFaturamentoGrupo().equals(obj.getFaturamentoGrupo().getId()) && objVO.getAnoMes().equals(obj.getAnoMes()) && objVO.getCiclo().equals(obj.getCiclo());
   }

}
