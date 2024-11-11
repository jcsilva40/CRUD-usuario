package com.stfn2.ggas.services.componentes.calculotributos;

import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.FaturaDTO;
import com.stfn2.ggas.domain.enumTypes.Rubricas;
import com.stfn2.ggas.domain.enumTypes.TributosEnum;
import com.stfn2.ggas.services.*;
import com.stfn2.ggas.tools.ToolList;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculosTributosComponent implements CalculosTributosInterface {

   private final TributoAliquotaService tributoAliquotaService;
   private final RubricaTributoService rubricaTributoService;
   private final PontoConsumoTributoAliquotaService pontoConsumoTributoAliquotaService;
   private final FaturaService faturaService;
   private final FaturaItemTributacaoService faturaItemTributacaoService;

   private Log log = new Log(this.getClass());

   public CalculosTributosComponent(TributoAliquotaService tributoAliquotaService,
                                    RubricaTributoService rubricaTributoService,
                                    PontoConsumoTributoAliquotaService pontoConsumoTributoAliquotaService,
                                    FaturaService faturaService,
                                    FaturaItemTributacaoService faturaItemTributacaoService) {
      this.tributoAliquotaService = tributoAliquotaService;
      this.rubricaTributoService = rubricaTributoService;
      this.pontoConsumoTributoAliquotaService = pontoConsumoTributoAliquotaService;
      this.faturaService = faturaService;
      this.faturaItemTributacaoService = faturaItemTributacaoService;
   }

   @Override
   public BigDecimal calcularValorFinalComTributos(BigDecimal valor, AliquotasVO aliquotas) {
      BigDecimal fator_1 = (BigDecimal.ONE.subtract(aliquotas.getPis())).subtract(aliquotas.getCofins()).subtract(aliquotas.getIss());
      BigDecimal fator_2 = (BigDecimal.ONE.subtract(aliquotas.getIcms()));

      valor = valor.divide(fator_1, 6, RoundingMode.HALF_UP);

      if (fator_2.compareTo(BigDecimal.ONE) == 0 | fator_2.compareTo(BigDecimal.ZERO) == 0) {
         valor = valor.setScale(4, RoundingMode.HALF_EVEN);
      } else {
         valor = valor.divide(fator_2, 6, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_EVEN);
      }

      return valor;
   }

   @Override
   public BigDecimal calcularValorFinalSemTributos(BigDecimal valor, AliquotasVO aliquotas) {
      BigDecimal fator_1 = (BigDecimal.ONE.subtract(aliquotas.getPis())).subtract(aliquotas.getCofins()).subtract(aliquotas.getIss());
      BigDecimal fator_2 = (BigDecimal.ONE.subtract(aliquotas.getIcms()));

      valor = valor.multiply(fator_1).setScale(6, RoundingMode.HALF_UP);

      if (fator_2.compareTo(BigDecimal.ONE) == 0 | fator_2.compareTo(BigDecimal.ZERO) == 0) {
         valor = valor.setScale(4, RoundingMode.HALF_EVEN);
      } else {
         valor = valor.multiply(fator_2).setScale(6, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_EVEN);
      }
      return valor;
   }

   @Override
   public AliquotasVO obterAliquotasTributos(List<Tributo> tributos) {
      AliquotasVO aliquotas = new AliquotasVO();
      for (Tributo tributo : tributos) {
         BigDecimal valorAliquota = tributoAliquotaService.obterAliquotaVigente(tributo).getValorAliquota();
         popularValorAliquotas(aliquotas, tributo.getDescricao(), valorAliquota);
      }
      return aliquotas;
   }

   @Override
   public List<RubricaTributo> obterTributosRubrica(Rubrica rubrica) {
      return rubricaTributoService.obterRubricaTributos(rubrica);
   }

   @Override
   public List<PontoConsumoTributoAliquota> verificarDiferimentoPontoConsumo(PontoConsumo pontoConsumo) {
      List<PontoConsumoTributoAliquota> diferimentos = pontoConsumoTributoAliquotaService.verificarDiferimentoPontoConsumo(pontoConsumo);
      return diferimentos;
   }

   @Override
   public void aplicarDiferimentoTributarioNasAliquotas(AliquotasVO aliquotas,
                                                        List<PontoConsumoTributoAliquota> diferimentos) {

      for(PontoConsumoTributoAliquota PCTA : diferimentos){
         popularValorAliquotas(aliquotas, PCTA.getTributo().getDescricao(), PCTA.getValor());
      }
   }

   public BigDecimal calcularDiferimentoTributarioFatura(Fatura fatura){
      BigDecimal valorFatura = BigDecimal.ZERO;
      BigDecimal valorSemDiferimento = BigDecimal.ZERO;
      BigDecimal valorAcumulado = BigDecimal.ZERO;
      BigDecimal diferenca = BigDecimal.ZERO;
      AliquotasVO aliquotasBase = new AliquotasVO();
      PontoConsumo pontoConsumo = fatura.getPontoConsumo();
      List<VrUnitarioVolumeVO> vrUnitarioVolumeContainers = new ArrayList<VrUnitarioVolumeVO>();
      List<Tributo> tributos = new ArrayList<>();

      List<PontoConsumoTributoAliquota> diferimentos = pontoConsumoTributoAliquotaService.verificarDiferimentoPontoConsumo(pontoConsumo);

      if(ToolList.notNullOrEmpyt(diferimentos)){
         if(diferimentos.size() == 1 && !diferimentos.stream().filter(d -> d.getTributo().getId() == TributosEnum.IRRF.getId()).findAny().isPresent()){
            PontoConsumoTributoAliquota pcta = diferimentos.get(0);

            FaturaItem faturaItem =
                    fatura.getListaFaturaItens().stream().filter(fait -> fait.getRubrica().getId() == Rubricas.CONSUMO_GAS.getId()).findFirst().get();

            if(faturaItem != null){
               List<FaturaItemImpressao> itensImpressao = faturaItem.getFaturaImpressao();

               for(FaturaItemImpressao itemImpressao : itensImpressao){
                  valorFatura = valorFatura.add(itemImpressao.getValorTotal());
                  vrUnitarioVolumeContainers.add(new VrUnitarioVolumeVO(itemImpressao.getValorUnitario(),
                          itemImpressao.getConsumo()));
               }

               Tributo tributoIsento = pcta.getTributo();

               if(tributoIsento.getDescricao().equals(TributosEnum.ICMS.getDescricao())){

                  //TODO: montar estrutura para obter os tributos / aliquotas a partir do ponto de consumo
                  popularValorAliquotas(aliquotasBase, tributos);

                  for(VrUnitarioVolumeVO vrUnitarioVolume : vrUnitarioVolumeContainers){
                     BigDecimal fator_1 = (BigDecimal.ONE.subtract(aliquotasBase.getPis()).subtract(aliquotasBase.getCofins()));
                     BigDecimal vrOriginal = vrUnitarioVolume.getVrUnitario().multiply(fator_1).setScale(4,
                             RoundingMode.HALF_EVEN);

                     vrOriginal = calcularValorFinalComTributos(vrOriginal, aliquotasBase);
                     valorSemDiferimento = vrOriginal.multiply(vrUnitarioVolume.getVolume());
                     valorSemDiferimento = valorSemDiferimento.divide(BigDecimal.ONE, 2, RoundingMode.HALF_EVEN);

                     valorAcumulado = valorAcumulado.add(valorSemDiferimento);
                  }
                  if(valorAcumulado.subtract(valorFatura).compareTo(BigDecimal.ZERO) > 0){
                     diferenca = valorAcumulado.subtract(valorFatura);
                     //TODO: verificar a necessidade de copiar a fatura antes de alterar os valores e salvar
                     fatura.setValorDiferimento(diferenca);
                     faturaService.createOrUpdate(MapperImpl.parseObject(fatura, FaturaDTO.class));
                  }
               }
            }
         } else {
            //TODO: implementação para o caso de mais de um diferimento no Ponto de Consumo
         }

      }

      return diferenca;
   }

   public void ajustarBaseTributos(Fatura fatura){
      List<FaturaItemTributacao> itensTributacao = new ArrayList<>();
      FaturaItemTributacao itemTributacaoPIS = null;
      FaturaItemTributacao itemTributacaoCOFINS = null;

      FaturaItem item = null;
      BigDecimal valorIcms = BigDecimal.ZERO;
      BigDecimal valorBaseIcms = BigDecimal.ZERO;

      Long idFatura = fatura.getId();

      try {
        item = new FaturaItem();//faturaService.consultarFaturaItemPorCodigoFatura(idFatura, 23L);
         if (item != null){
            itensTributacao = faturaService.listarFaturaItemTributacao(item.getId());
         }

      } catch (Exception e) {
         log.erro("Erro ao buscar os itens da fatura para ajuste de base de calculo.", e.getMessage());
      }

      for(FaturaItemTributacao itemTributacao : itensTributacao){
         switch (itemTributacao.getTributo().getDescricao()) {
            case "ICMS":
               valorIcms = itemTributacao.getValorTributo();
               valorBaseIcms = itemTributacao.getBaseCalculoTributo();
               break;
            case "PIS":
               itemTributacaoPIS = itemTributacao;
               break;
            case "COFINS":
               itemTributacaoCOFINS = itemTributacao;
               break;
         }
      }

      if(valorIcms.compareTo(BigDecimal.ZERO) > 0){
         ajustarValorItens(itemTributacaoPIS, valorIcms, valorBaseIcms);
         ajustarValorItens(itemTributacaoCOFINS, valorIcms, valorBaseIcms);
      }
   }

   public BigDecimal getValorTotalISS(Fatura fatura) {
      BigDecimal issTotal = BigDecimal.ZERO;
      List<FaturaItem> listaFaturaItem = faturaService.listarFaturaItemPorFatura(fatura.getId());
      for (FaturaItem item : listaFaturaItem) {
         try {
            List<FaturaItemTributacao> itensTributacao = faturaService
                    .listarFaturaItemTributacao(item.getId()).stream().collect(Collectors.toList());
            for (FaturaItemTributacao itemTributacao : itensTributacao) {
               if (itemTributacao.getTributo().getDescricao().equals("ISS")) {
                  issTotal = issTotal.add(itemTributacao.getValorTributo());
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      return issTotal;
   }

   private void ajustarValorItens(FaturaItemTributacao item, BigDecimal valorIcms, BigDecimal valorBaseIcms) {
      BigDecimal baseTributo = BigDecimal.ZERO;
      BigDecimal valorTributo = BigDecimal.ZERO;

      baseTributo = valorBaseIcms.subtract(valorIcms).setScale(2, RoundingMode.HALF_EVEN);
      valorTributo = baseTributo.multiply(item.getPercentualTributo()).setScale(2, RoundingMode.HALF_EVEN);

      item.setBaseCalculoTributo(baseTributo);
      item.setValorTributo(valorTributo);

      try {
         faturaItemTributacaoService.save(item);
      } catch ( Exception e) {
         e.printStackTrace();
      }
   }

   private void popularValorAliquotas(AliquotasVO aliquotas, List<Tributo> tributos){
      for(Tributo tributo : tributos){
         BigDecimal valorAliquota = BigDecimal.ZERO;
         popularValorAliquotas(aliquotas, tributo.getDescricao(), valorAliquota);
      }
   }

   private void popularValorAliquotas(AliquotasVO aliquotas, String descricaoTributo, BigDecimal valor){

      if (descricaoTributo.equals(TributosEnum.ICMS.getDescricao())) {
         aliquotas.setIcms(valor);
      }
      if (descricaoTributo.equals(TributosEnum.PIS.getDescricao())) {
         aliquotas.setPis(valor);
      }
      if (descricaoTributo.equals(TributosEnum.COFINS.getDescricao())) {
         aliquotas.setCofins(valor);
      }
      if (descricaoTributo.equals(TributosEnum.ISS.getDescricao())) {
         aliquotas.setIss(valor);
      }
      if (descricaoTributo.equals(TributosEnum.IRRF.getDescricao())) {
         aliquotas.setIss(valor);
      }
   }
}
