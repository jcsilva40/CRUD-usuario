package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.config.exceptions.types.ValidationErrorException;
import com.stfn2.ggas.config.exceptions.utils.FieldMessage;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ContratoDTO;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoEnderecoFaturaDTO;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoItemFaturamentoDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.*;
import com.stfn2.ggas.repositories.ArrecadadorContratoConvenioRepository;
import com.stfn2.ggas.repositories.ContratoRepository;
import com.stfn2.ggas.services.componentes.debitoAutomatico.DebitoAutomaticoComponent;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolStr;
import com.stfn2.ggas.tools.ToolUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Service
public class ContratoService extends BaseService<Contrato, ContratoDTO, ContratoBasicDTO, ContratoFilterDTO, ContratoRepository> {
        
    @Autowired
    private ArrecadadorContratoConvenioRepository arrecadadorContratoConvenioRepository;
    
    @Autowired
    private PressaoFornecimentoFaixaService pressaoFornecimentoFaixaService;
    
    @Autowired
    private DebitoAutomaticoComponent debitoAutomaticoComponent;

    private static final Integer NUMERO_ADITIVO = 1;
    private static final Long CONTRATO_MODELO = 68l;
    
    @Override
    public Page<ContratoBasicDTO> findAll(ContratoFilterDTO filter, Pageable pageable) {  
        String cliente = filter.getCliente();
        Integer nip = filter.getNip();
        String cil = filter.getCil();
        Boolean habilitado = filter.getHabilitado();
        Page<ContratoBasicDTO> result = this.repository.findAll(cliente, nip, cil, habilitado, pageable).map(projection -> MapperImpl.parseObject(projection, ContratoBasicDTO.class));
        return result;
    }
    
    @Override
    public ContratoDTO entityToDTO(Contrato contrato){
        ContratoDTO contratoDTO = new ContratoDTO();
        getDadosReferenteContrato(contrato, contratoDTO);
        getDadosReferenteContratoPontoConsumo(contrato, contratoDTO);
        getDadosReferenteContratoCliente(contrato, contratoDTO);
        getDadosReferenteArrecadador(contrato, contratoDTO);
        return contratoDTO;
    }

    private void getDadosReferenteContrato(Contrato contrato, ContratoDTO contratoDTO){
        String numeroContrato = String.valueOf(contrato.getAnoContrato()) + ToolStr.formatNumber6Digits(contrato.getNumero());
        contratoDTO.setId(contrato.getId());
        contratoDTO.setNumeroContrato(numeroContrato);
        contratoDTO.setNumero(contrato.getNumero());
        contratoDTO.setAnoContrato(contrato.getAnoContrato());
        contratoDTO.setNumeroDebitoAutomatico(contrato.getNumeroClienteDebito());
        if(!Objects.isNull(contrato.getClienteAssinatura())){
            contratoDTO.setClienteAssinaturaId(contrato.getClienteAssinatura().getId());
        }
        contratoDTO.setDataAssinatura(contrato.getDataAssinatura());
        contratoDTO.setDataLiberacaoGas(contrato.getDataLiberacaoGas());
        contratoDTO.setIndicadorParticipanteECartas(contrato.getIndicadorParticipanteECartas());
        if(!Objects.isNull(contrato.getTipoAgrupamento())){
            contratoDTO.setTipoAgrupamentoId(contrato.getTipoAgrupamento().getId());
        }
        
        contratoDTO.setIndicadorMulta(contrato.getIndicadorMulta());
        contratoDTO.setIndicadorJuros(contrato.getIndicadorJuros());
        contratoDTO.setPercentualJuros(contrato.getPercentualJurosMora());
        contratoDTO.setPercentualMulta(contrato.getPercentualMulta());
        if(!Objects.isNull(contrato.getIndiceFinanceiro())){
            IndiceFinanceiro indiceFinanceiro = contrato.getIndiceFinanceiro();            
            contratoDTO.setIndiceFinanceiroId(indiceFinanceiro.getId());
        }
        if(!Objects.isNull(contrato.getTipoCalculoCorrecaoMonetaria())){
            EntidadeConteudo tipoCalculoCorrecaoMonetaria = contrato.getTipoCalculoCorrecaoMonetaria();            
            contratoDTO.setTipoCalculoCorrecaoMonetariaId(tipoCalculoCorrecaoMonetaria.getId());
        }
        if(!Objects.isNull(contrato.getGarantiaFinanceira())){
            contratoDTO.setGarantiaFinanceiraId(contrato.getGarantiaFinanceira().getId());
        }
        contratoDTO.setDescricaoGarantiaFinanceira(contrato.getDescricaoGarantiaFinanceira());
        contratoDTO.setValorGarantiaFinanceira(contrato.getValorGarantiaFinanceira());
        if(!Objects.isNull(contrato.getContratoSituacao())){
            contratoDTO.setContratoSituacao(contrato.getContratoSituacao());
        }
        contratoDTO.setDataVencimentoObrigacoes(contrato.getDataVencimentoObrigacoes());
        if(!Objects.isNull(contrato.getTipoVinculoContratoEnum())){
            contratoDTO.setTipoVinculoContratoId(contrato.getTipoVinculoContratoEnum().getId());
        }
        contratoDTO.setDataCriacao(contrato.getDataCriacao());
    }
    
    private void getDadosReferenteContratoPontoConsumo(Contrato contrato, ContratoDTO contratoDTO){
        if(!Objects.isNull(contrato.getContratoPontoConsumo())){
            ContratoPontoConsumo contratoPontoConsumo = contrato.getContratoPontoConsumo();            
            contratoDTO.setContratoPontoConsumoId(contratoPontoConsumo.getId());
            //Vazão
            contratoDTO.setMedidaVazaoMaximaInstantanea(contratoPontoConsumo.getMedidaVazaoMaximaInstantanea());
            if(!Objects.isNull(contratoPontoConsumo.getUnidadeVazaoMaximaInstantanea())){
                contratoDTO.setUnidadeVazaoMaximaInstantaneaId(contratoPontoConsumo.getUnidadeVazaoMaximaInstantanea().getId());
            }
            contratoDTO.setMedidaPressao(contratoPontoConsumo.getMedidaPressao());            
            
            //Consumo
            if(!Objects.isNull(contratoPontoConsumo.getRegimeConsumo())){
                contratoDTO.setRegimeConsumoId(contratoPontoConsumo.getRegimeConsumo().getId());
            }
            contratoDTO.setMedidaFornecimentoMaxDiaria(contratoPontoConsumo.getMedidaFornecimentoMaxDiaria());
            if(!Objects.isNull(contratoPontoConsumo.getUnidadeFornecimentoMaxDiaria())){
                contratoDTO.setUnidadeFornecimentoMaxDiariaId(contratoPontoConsumo.getUnidadeFornecimentoMaxDiaria().getId());
            }
            contratoDTO.setMedidaFornecimentoMinMensal(contratoPontoConsumo.getMedidaFornecimentoMinMensal());
            if(!Objects.isNull(contratoPontoConsumo.getUnidadeFornecimentoMinMensal())){
                contratoDTO.setUnidadeFornecimentoMinMensalId(contratoPontoConsumo.getUnidadeFornecimentoMinMensal().getId());
            }
            
            //PCS
            if(!Objects.isNull(contratoPontoConsumo.getContratoPontoConsumoPCSAmostragem())){
                if(!Objects.isNull(contratoPontoConsumo.getContratoPontoConsumoPCSAmostragem().getLocalAmostragemPCS())){
                    contratoDTO.setLocalAmostragemPCSId(contratoPontoConsumo.getContratoPontoConsumoPCSAmostragem().getLocalAmostragemPCS().getId());
                }
            }
            if(!Objects.isNull(contratoPontoConsumo.getContratoPontoConsumoPCSIntervalo())){
                if(!Objects.isNull(contratoPontoConsumo.getContratoPontoConsumoPCSIntervalo().getPcsIntervalo())){
                    contratoDTO.setPcsIntervaloId(contratoPontoConsumo.getContratoPontoConsumoPCSIntervalo().getPcsIntervalo().getId());
                }
            }
            
            
            contratoDTO.setEnvioFaturaFisica(contratoPontoConsumo.getEnvioFaturaFisica());
            //Endereco Fatura            
            contratoDTO.setEnderecoFatura(getEnderecoFaturaDTO(contratoPontoConsumo));
            contratoDTO.setEmailEntrega(contratoPontoConsumo.getEmailEntrega());
            if(!Objects.isNull(contratoPontoConsumo.getPontoConsumo())){
                PontoConsumo pontoConsumo = contratoPontoConsumo.getPontoConsumo();
                if(!Objects.isNull(pontoConsumo.getId())){
                    contratoDTO.setPontoConsumoId(pontoConsumo.getId());                    
                }                
            }
            if(!Objects.isNull(contratoPontoConsumo.getContratoCompra())){
                EntidadeConteudo contratoCompra = contratoPontoConsumo.getContratoCompra();
                contratoDTO.setContratoCompraId(contratoCompra.getId());
            } 
            if(!Objects.isNull(contratoPontoConsumo.getPeriodicidade())){
                Periodicidade periodicidade = contratoPontoConsumo.getPeriodicidade();
                contratoDTO.setPeriodicidadeCompraId(periodicidade.getId());
            }
            contratoDTO.setEmiteNotaFiscalEletronica(contratoPontoConsumo.getEmiteNotaFiscalEletronica());
            contratoDTO.setEmitirFaturaComNfe(contratoPontoConsumo.getEmitirFaturaComNfe());
            getDadosReferenteContratoPontoConsumoItemFaturamento(contratoPontoConsumo, contratoDTO);
        } 
    }
    
    private void getDadosReferenteContratoPontoConsumoItemFaturamento(ContratoPontoConsumo contratoPontoConsumo, ContratoDTO contratoDTO){
        if(!Objects.isNull(contratoPontoConsumo.getListaContratoPontoConsumoItemFaturamento())){
            List<ContratoPontoConsumoItemFaturamento> listaContratoItemFaturamento = contratoPontoConsumo.getListaContratoPontoConsumoItemFaturamento();
            List<ContratoPontoConsumoItemFaturamentoDTO> listaContratoItemFaturamentoDTO = new ArrayList<>();
            for(ContratoPontoConsumoItemFaturamento contratoItemFaturamento : listaContratoItemFaturamento){
                ContratoPontoConsumoItemFaturamentoDTO contratoItemFaturamentoDTO = new ContratoPontoConsumoItemFaturamentoDTO();
                contratoItemFaturamentoDTO.setId(contratoItemFaturamento.getId());
                if(!Objects.isNull(contratoItemFaturamento.getItemFatura())){
                    contratoItemFaturamentoDTO.setItemFaturaId(contratoItemFaturamento.getItemFatura().getId());
                }
                //Fazer Tarifa
                if(!Objects.isNull(contratoItemFaturamento.getTarifa())){
                    contratoItemFaturamentoDTO.setTarifaId(contratoItemFaturamento.getTarifa().getId());
                }
                contratoItemFaturamentoDTO.setPercentualMinimoQDC(contratoItemFaturamento.getPercentualMinimoQDC());
                contratoItemFaturamentoDTO.setNumeroDiaVencimento(contratoItemFaturamento.getNumeroDiaVencimento());
                if(!Objects.isNull(contratoItemFaturamento.getFaseReferencia())){
                    contratoItemFaturamentoDTO.setFaseReferenciaId(contratoItemFaturamento.getFaseReferencia().getId());
                }
                if(!Objects.isNull(contratoItemFaturamento.getOpcaoFaseReferencia())){
                    contratoItemFaturamentoDTO.setOpcaoFaseReferenciaId(contratoItemFaturamento.getOpcaoFaseReferencia().getId());
                }
                contratoItemFaturamentoDTO.setVencimentoDiaUtil(contratoItemFaturamento.getVencimentoDiaUtil());
                listaContratoItemFaturamentoDTO.add(contratoItemFaturamentoDTO);
            }
            contratoDTO.setListaContratoItemFaturamento(listaContratoItemFaturamentoDTO);
        }
    }
    
    private void getDadosReferenteContratoCliente(Contrato contrato, ContratoDTO contratoDTO){
        if(!Objects.isNull(contrato.getContratoCliente())){
            ContratoCliente contratoCliente = contrato.getContratoCliente();
            if(!Objects.isNull(contratoCliente.getCliente())){
                contratoDTO.setClienteResponsabilidadeId(contratoCliente.getCliente().getId());
            }
            if(!Objects.isNull(contratoCliente.getResponsabilidade())){                
                contratoDTO.setResponsabilidadeId(contratoCliente.getResponsabilidade().getId());                
            }
            contratoDTO.setDataRelacaoInicio(contratoCliente.getDataRelacaoInicio());
        }
    }
    
    private ContratoPontoConsumoEnderecoFaturaDTO getEnderecoFaturaDTO(ContratoPontoConsumo contratoPontoConsumo) {
      ContratoPontoConsumoEnderecoFaturaDTO enderecoFatura = new ContratoPontoConsumoEnderecoFaturaDTO();
      enderecoFatura.setLogradouro(contratoPontoConsumo.getLogradouroFatura());
      enderecoFatura.setMunicipioFatura(contratoPontoConsumo.getMunicipioFatura());
      enderecoFatura.setUfFatura(contratoPontoConsumo.getUfFatura());
      enderecoFatura.setBairroFatura(contratoPontoConsumo.getBairroFatura());
      enderecoFatura.setCepFatura(contratoPontoConsumo.getCepFatura());
      enderecoFatura.setComplementoFatura(contratoPontoConsumo.getComplementoFatura());
      enderecoFatura.setNumeroImovelFatura(contratoPontoConsumo.getNumeroImovelFatura());
      return enderecoFatura;
   }
    
    private void getDadosReferenteArrecadador(Contrato contrato, ContratoDTO contratoDTO){
        if(!Objects.isNull(contrato.getFormaCobranca())){
            FormaCobrancaEnum formaCobranca = contrato.getFormaCobranca();
            contratoDTO.setFormaCobrancaId(formaCobranca.getId());
            if(formaCobranca.equals(FormaCobrancaEnum.BOLETO_BANCARIO)){
                if(!Objects.isNull(contrato.getArrecadadorContratoConvenio())){
                    ArrecadadorContratoConvenio arrecadadorContratoConvenio = contrato.getArrecadadorContratoConvenio();
                    contratoDTO.setArrecadadorContratoConvenioId(arrecadadorContratoConvenio.getId());                    
                }
            }
            if(formaCobranca.equals(FormaCobrancaEnum.DEBITO_AUTOMATICO)){
                if(!Objects.isNull(contrato.getArrecadadorContratoConvenioDebitoAutomatico())){
                    ArrecadadorContratoConvenio arrecadadorContratoConvenioDebitoAutomatico = contrato.getArrecadadorContratoConvenioDebitoAutomatico();
                    contratoDTO.setArrecadadorContratoConvenioId(arrecadadorContratoConvenioDebitoAutomatico.getId());                    
                    //arrecadadorContratoConvenioDebitoAutomatico.getArrecadadorContrato().getArrecadador().getBanco().getId();
                    if(!Objects.isNull(contrato.getBanco())){
                        contratoDTO.setBancoId(contrato.getBanco().getId());
                    }
                    contratoDTO.setAgencia(contrato.getAgencia());
                    contratoDTO.setContaCorrente(contrato.getContaCorrente());                    
                }
            }            
        }
    }    

    @Transactional
    @Override
    public ContratoDTO createOrUpdate(ContratoDTO objeto) {
        List<FieldMessage> erros = new ArrayList<>();
        validDto(objeto);

        if (!erros.isEmpty()) {
            throw new ValidationErrorException(erros);
        }

        Contrato objetoDB = dtoToEntity(objeto);
        objetoDB.updateVersao();
        mudarContratosAbertosParaCancelado(objeto);
        var newObj = this.save(objetoDB);
        ContratoDTO dto = entityToDTO(newObj);
        return dto;
    }
    
    @Override
    public Contrato dtoToEntity(ContratoDTO contratoDTO){
        Contrato contrato = new Contrato();
        PontoConsumo pontoConsumo = null;
        if(!Objects.isNull(contratoDTO.getPontoConsumoId())){
            pontoConsumo = new PontoConsumo();
            pontoConsumo.setId(contratoDTO.getPontoConsumoId());            
        }
        salvarDadosReferenteContrato(contrato, contratoDTO, pontoConsumo);
        salvarDadosReferenteContratoPontoConsumo(contrato, contratoDTO, pontoConsumo);
        salvarDadosReferenteContratoCliente(contrato, contratoDTO, pontoConsumo);
        salvarDadosReferenteArrecadador(contrato, contratoDTO);
        
        //implementar qdc
        return contrato;
    }

    private void salvarDadosReferenteContrato(Contrato contrato, ContratoDTO contratoDTO, PontoConsumo pontoConsumo){
        contrato.setId(contratoDTO.getId());
        if(Objects.isNull(contratoDTO.getAnoContrato())){
            contrato.setAnoContrato(ToolDate.getCurrentYear());
        }
        else{
            contrato.setAnoContrato(contratoDTO.getAnoContrato());
        }
        contrato.setDataAssinatura(contratoDTO.getDataAssinatura());
        contrato.setDataLiberacaoGas(contratoDTO.getDataLiberacaoGas());
        contrato.setIndicadorParticipanteECartas(contratoDTO.getIndicadorParticipanteECartas());
        if(!Objects.isNull(contratoDTO.getTipoAgrupamentoId())){
            EntidadeConteudo tipoAgrupamento = new EntidadeConteudo();
            tipoAgrupamento.setId(contratoDTO.getTipoAgrupamentoId());
            contrato.setTipoAgrupamento(tipoAgrupamento);
        }
        contrato.setIndicadorMulta(contratoDTO.getIndicadorMulta());
        contrato.setIndicadorJuros(contratoDTO.getIndicadorJuros());
        contrato.setPercentualJurosMora(contratoDTO.getPercentualJuros());
        contrato.setPercentualMulta(contratoDTO.getPercentualMulta());        
        contrato.setDescricaoGarantiaFinanceira(contratoDTO.getDescricaoGarantiaFinanceira());
        if(!Objects.isNull(contratoDTO.getGarantiaFinanceiraId())){
            EntidadeConteudo garantiaFinanceira = new EntidadeConteudo();
            garantiaFinanceira.setId(contratoDTO.getGarantiaFinanceiraId());
            contrato.setGarantiaFinanceira(garantiaFinanceira);
        }
        contrato.setValorGarantiaFinanceira(contratoDTO.getValorGarantiaFinanceira());        
        contrato.setNumeroAditivo(NUMERO_ADITIVO);
        contrato.setRenovacaoAutomatica(false);
	contrato.setAgrupamentoConta(false);
	contrato.setAgrupamentoCobranca(false);
        if(Objects.isNull(contratoDTO.getId()) || Objects.isNull(contratoDTO.getContratoSituacao())){
            contrato.setContratoSituacao(ContratoSituacaoEnum.ATIVO);
        }
        else if(contratoDTO.getContratoSituacao().equals(ContratoSituacaoEnum.ATIVO)){
            contrato.setContratoSituacao(ContratoSituacaoEnum.ALTERADO);
        }
        else{
            contrato.setContratoSituacao(contratoDTO.getContratoSituacao());
        }
        
        if(Objects.isNull(contratoDTO.getNumero())){
            contrato.setNumero(repository.getNextNumberOfContrato(Year.now().getValue()));
        }
        else{
            contrato.setNumero(contratoDTO.getNumero());
        }
        
        contrato.setNumeroClienteDebito(contratoDTO.getNumeroDebitoAutomatico());
        if(Objects.isNull(contratoDTO.getNumeroDebitoAutomatico()) || contratoDTO.getNumeroDebitoAutomatico().isEmpty()){
            String numeroDebitoAutomatico = debitoAutomaticoComponent.gerarNumeroCliente(pontoConsumo.getId());
            contrato.setNumeroClienteDebito(numeroDebitoAutomatico);
        }        
        if(!Objects.isNull(contratoDTO.getTipoVinculoContratoId())){
            contrato.setTipoVinculoContratoEnum(TipoVinculoContratoEnum.toEnum(contratoDTO.getTipoVinculoContratoId()));
        }
        contrato.setDataVencimentoObrigacoes(contratoDTO.getDataVencimentoObrigacoes());
        if(!Objects.isNull(contratoDTO.getDataCriacao())){
            contrato.setDataCriacao(contratoDTO.getDataCriacao());
        }
        contrato.setContratoModelo(CONTRATO_MODELO);
    }
    
    private void salvarDadosReferenteContratoPontoConsumo(Contrato contrato, ContratoDTO contratoDTO, PontoConsumo pontoConsumo){
        ContratoPontoConsumo contratoPontoConsumo = new ContratoPontoConsumo();
        contratoPontoConsumo.setId(contratoDTO.getContratoPontoConsumoId());
        if(!Objects.isNull(contratoDTO.getIndiceFinanceiroId())){
            IndiceFinanceiro indiceFinanceiro = new IndiceFinanceiro();
            indiceFinanceiro.setId(contratoDTO.getIndiceFinanceiroId());
            contrato.setIndiceFinanceiro(indiceFinanceiro);
        }
        if(!Objects.isNull(contratoDTO.getTipoCalculoCorrecaoMonetariaId())){
            EntidadeConteudo tipoCalculoCorrecaoMonetaria = new EntidadeConteudo();
            tipoCalculoCorrecaoMonetaria.setId(contratoDTO.getTipoCalculoCorrecaoMonetariaId());
            contrato.setTipoCalculoCorrecaoMonetaria(tipoCalculoCorrecaoMonetaria);
        }
        //Vazão
        contratoPontoConsumo.setMedidaVazaoMaximaInstantanea(contratoDTO.getMedidaVazaoMaximaInstantanea());
        if(!Objects.isNull(contratoDTO.getUnidadeVazaoMaximaInstantaneaId())){
            Unidade unidadeVazaoMaximaInstantanea = new Unidade();
            unidadeVazaoMaximaInstantanea.setId(contratoDTO.getUnidadeVazaoMaximaInstantaneaId());
            contratoPontoConsumo.setUnidadeVazaoMaximaInstantanea(unidadeVazaoMaximaInstantanea);
        }        
        contratoPontoConsumo.setMedidaPressao(contratoDTO.getMedidaPressao());
        contratoPontoConsumo.setMedidaFornecimentoMaxDiaria(contratoDTO.getMedidaFornecimentoMaxDiaria());
        if(!Objects.isNull(contratoDTO.getUnidadeFornecimentoMaxDiariaId())){
                Unidade unidadeFornecimentoMaxDiaria = new Unidade();
                unidadeFornecimentoMaxDiaria.setId(contratoDTO.getUnidadeFornecimentoMaxDiariaId());
                contratoPontoConsumo.setUnidadeFornecimentoMaxDiaria(unidadeFornecimentoMaxDiaria);
            }
        contratoPontoConsumo.setMedidaFornecimentoMinMensal(contratoDTO.getMedidaFornecimentoMinMensal());
        if(!Objects.isNull(contratoDTO.getUnidadeFornecimentoMinMensalId())){
            Unidade unidadeFornecimentoMinMensal = new Unidade();
            unidadeFornecimentoMinMensal.setId(contratoDTO.getUnidadeFornecimentoMinMensalId());
            contratoPontoConsumo.setUnidadeFornecimentoMinMensal(unidadeFornecimentoMinMensal);
        }
        //Consumo
        if(!Objects.isNull(contratoDTO.getRegimeConsumoId())){
            EntidadeConteudo regimeConsumo = new EntidadeConteudo();
            regimeConsumo.setId(contratoDTO.getRegimeConsumoId());
            contratoPontoConsumo.setRegimeConsumo(regimeConsumo);
        }

        //PCS
        if(!Objects.isNull(contratoDTO.getLocalAmostragemPCSId())){
            ContratoPontoConsumoPCSAmostragem contratoPontoConsumoLocalAmostragemPCS = new ContratoPontoConsumoPCSAmostragem();
            EntidadeConteudo localAmostragemPCS = new EntidadeConteudo();            
            localAmostragemPCS.setId(contratoDTO.getLocalAmostragemPCSId());
            contratoPontoConsumoLocalAmostragemPCS.setPrioridade(1);
            contratoPontoConsumoLocalAmostragemPCS.setLocalAmostragemPCS(localAmostragemPCS); 
            contratoPontoConsumoLocalAmostragemPCS.setContratoPontoConsumo(contratoPontoConsumo);
            contratoPontoConsumo.setContratoPontoConsumoPCSAmostragem(contratoPontoConsumoLocalAmostragemPCS);
        }
        if(!Objects.isNull(contratoDTO.getPcsIntervaloId())){
            ContratoPontoConsumoPCSIntervalo pcsIntervalo = new ContratoPontoConsumoPCSIntervalo();
            pcsIntervalo.setPrioridade(1);
            pcsIntervalo.setPcsIntervalo(PCSIntervaloEnum.toEnum(contratoDTO.getPcsIntervaloId()));
            pcsIntervalo.setContratoPontoConsumo(contratoPontoConsumo);
            contratoPontoConsumo.setContratoPontoConsumoPCSIntervalo(pcsIntervalo);            
        }        
        
        contratoPontoConsumo.setEnvioFaturaFisica(contratoDTO.getEnvioFaturaFisica());
        contratoPontoConsumo.setEmailEntrega(contratoDTO.getEmailEntrega());
        salvarDadosReferenteEnderecoFatura(contratoPontoConsumo, contratoDTO);        
        contratoPontoConsumo.setPontoConsumo(pontoConsumo);
        contratoPontoConsumo.setPressaoFornecimentoFaixa(pressaoFornecimentoFaixaService.findPressaoFornecimentoFaixaPorPontoConsumo(pontoConsumo.getId()));
        if(!Objects.isNull(contratoDTO.getContratoCompraId())){
            EntidadeConteudo contratoCompra = new EntidadeConteudo();
            contratoCompra.setId(contratoDTO.getContratoCompraId());
            contratoPontoConsumo.setContratoCompra(contratoCompra);
        }
        if(!Objects.isNull(contratoDTO.getContratoCompraId())){
            Periodicidade periodicidade = new Periodicidade();
            periodicidade.setId(contratoDTO.getPeriodicidadeCompraId());
            contratoPontoConsumo.setPeriodicidade(periodicidade);
        }
        contratoPontoConsumo.setEmiteNotaFiscalEletronica(contratoDTO.getEmiteNotaFiscalEletronica());
        contratoPontoConsumo.setEmitirFaturaComNfe(contratoDTO.getEmitirFaturaComNfe());
        salvarDadosReferenteContratoPontoConsumoItemFaturamento(contratoPontoConsumo, contratoDTO);
        contrato.setContratoPontoConsumo(contratoPontoConsumo);
        contratoPontoConsumo.setContrato(contrato);
        contratoPontoConsumo.setMedicaoTipo(MedicaoTipoEnum.DIARIA);
    }
    
    private void salvarDadosReferenteContratoPontoConsumoItemFaturamento(ContratoPontoConsumo contratoPontoConsumo, ContratoDTO contratoDTO) {
      if (!Objects.isNull(contratoDTO.getListaContratoItemFaturamento())) {
         List<ContratoPontoConsumoItemFaturamento> listaContratoItemFaturamento = new ArrayList();
         List<ContratoPontoConsumoItemFaturamentoDTO> listaItemFaturamentoDTO = contratoDTO.getListaContratoItemFaturamento();
         for (ContratoPontoConsumoItemFaturamentoDTO itemFaturamentoDTO : listaItemFaturamentoDTO) {
            ContratoPontoConsumoItemFaturamento itemFaturamento = new ContratoPontoConsumoItemFaturamento();
            itemFaturamento.setId(itemFaturamentoDTO.getId());
            if (!Objects.isNull(itemFaturamentoDTO.getItemFaturaId())) {
               itemFaturamento.setItemFatura(ItemFaturaEnum.toEnum(itemFaturamentoDTO.getItemFaturaId()));
            }
            if (!Objects.isNull(itemFaturamentoDTO.getTarifaId())) {
               Tarifa tarifa = new Tarifa();
               tarifa.setId(itemFaturamentoDTO.getTarifaId());
               itemFaturamento.setTarifa(tarifa);
            }
            itemFaturamento.setPercentualMinimoQDC(itemFaturamentoDTO.getPercentualMinimoQDC());
            itemFaturamento.setNumeroDiaVencimento(itemFaturamentoDTO.getNumeroDiaVencimento());
            if (!Objects.isNull(itemFaturamentoDTO.getOpcaoFaseReferenciaId())) {
               itemFaturamento.setOpcaoFaseReferencia(OpcaoFaseReferenciaEnum.toEnum(itemFaturamentoDTO.getOpcaoFaseReferenciaId()));
            }
            if (!Objects.isNull(itemFaturamentoDTO.getFaseReferenciaId())) {
               itemFaturamento.setFaseReferencia(FaseReferenciaEnum.toEnum(itemFaturamentoDTO.getFaseReferenciaId()));
            }
            itemFaturamento.setVencimentoDiaUtil(itemFaturamentoDTO.getVencimentoDiaUtil());
            itemFaturamento.setContratoPontoConsumo(contratoPontoConsumo);
            listaContratoItemFaturamento.add(itemFaturamento);
         }
         contratoPontoConsumo.setListaContratoPontoConsumoItemFaturamento(listaContratoItemFaturamento);
      }
   } 

    private void salvarDadosReferenteContratoCliente(Contrato contrato, ContratoDTO contratoDTO, PontoConsumo pontoConsumo){
        Cliente clienteAssinatura = new Cliente();
        if(!Objects.isNull(contratoDTO.getClienteAssinaturaId())){
            clienteAssinatura.setId(contratoDTO.getClienteAssinaturaId());
            contrato.setClienteAssinatura(clienteAssinatura);
        }        
        ContratoCliente contratoCliente = new ContratoCliente();
        Cliente cliente = new Cliente();
        EntidadeConteudo responsabilidade = new EntidadeConteudo();
        if(!Objects.isNull(contratoDTO.getClienteResponsabilidadeId())){
            cliente.setId(contratoDTO.getClienteResponsabilidadeId());            
        }
        if(!Objects.isNull(contratoDTO.getResponsabilidadeId())){
            responsabilidade.setId(contratoDTO.getResponsabilidadeId());
        }
        contratoCliente.setDataRelacaoInicio(contratoDTO.getDataRelacaoInicio());
        contratoCliente.setResponsabilidade(responsabilidade);
        contratoCliente.setCliente(cliente);        
        contratoCliente.setPontoConsumo(pontoConsumo);
        contratoCliente.setContrato(contrato);
        contrato.setContratoCliente(contratoCliente);
    }

    private void salvarDadosReferenteArrecadador(Contrato contrato, ContratoDTO contratoDTO){
        contrato.setFormaCobranca(FormaCobrancaEnum.toEnum(contratoDTO.getFormaCobrancaId()));
        if(contratoDTO.getFormaCobrancaId().equals(FormaCobrancaEnum.BOLETO_BANCARIO.getId())){
            ArrecadadorContratoConvenio arrecadadorContratoConvenio = arrecadadorContratoConvenioRepository
                                                                        .findById(contratoDTO.getArrecadadorContratoConvenioId())
                                                                        .orElseThrow();            
            contrato.setArrecadadorContratoConvenio(arrecadadorContratoConvenio);
            contrato.setIndicadorDebitoAutomatico(false);
        }
        else if(contratoDTO.getFormaCobrancaId().equals(FormaCobrancaEnum.DEBITO_AUTOMATICO.getId())){            
            contrato.setIndicadorDebitoAutomatico(true);
            Banco banco = new Banco();
            banco.setId(contratoDTO.getBancoId());
            contrato.setBanco(banco);
            contrato.setContaCorrente(contratoDTO.getContaCorrente());
            contrato.setAgencia(contratoDTO.getAgencia());
            //Fazer abaixo a pesquisa pelo id selecionado
            ArrecadadorContratoConvenio arrecadadorContratoConvenio = arrecadadorContratoConvenioRepository
                                                                        .findById(contratoDTO.getArrecadadorContratoConvenioId())
                                                                        .orElseThrow();
            contrato.setArrecadadorContratoConvenio(arrecadadorContratoConvenio);
            /*EntidadeConteudo tipoConvenio = new EntidadeConteudo();
            tipoConvenio.setId(FormaCobrancaEnum.DEBITO_AUTOMATICO.getId());*/
            arrecadadorContratoConvenio.setTipoConvenio(TipoConvenioBancarioEnum.DEBITO_AUTOMATICO);
            arrecadadorContratoConvenio.getArrecadadorContrato().getArrecadador().setBanco(banco);
            //Buscar o documento layoult do entradaDebitoAutomatico
            //arrecadadorContrato.setLeiaute(layoutEntradadebitoAutomatico);
            contrato.setArrecadadorContratoConvenioDebitoAutomatico(arrecadadorContratoConvenio);
        }
    }

    private void salvarDadosReferenteEnderecoFatura(ContratoPontoConsumo contratoPontoConsumo, ContratoDTO contratoDTO){
        if(!Objects.isNull(contratoDTO.getEnderecoFatura())){
            ContratoPontoConsumoEnderecoFaturaDTO enderecoFatura = contratoDTO.getEnderecoFatura();
            contratoPontoConsumo.setLogradouroFatura(enderecoFatura.getLogradouro());
            contratoPontoConsumo.setMunicipioFatura(enderecoFatura.getMunicipioFatura());
            contratoPontoConsumo.setUfFatura(enderecoFatura.getUfFatura());
            contratoPontoConsumo.setBairroFatura(enderecoFatura.getBairroFatura());
            contratoPontoConsumo.setCepFatura(enderecoFatura.getCepFatura());
            contratoPontoConsumo.setComplementoFatura(enderecoFatura.getComplementoFatura());
            contratoPontoConsumo.setNumeroImovelFatura(enderecoFatura.getNumeroImovelFatura());
            Cep cep = new Cep();
            cep.setId(Long.valueOf(enderecoFatura.getCepFatura().replaceAll("-","")));
            contratoPontoConsumo.setCep(cep);
            contratoPontoConsumo.setNumeroImovel(enderecoFatura.getNumeroImovelFatura());
            contratoPontoConsumo.setComplementoEndereco(enderecoFatura.getComplementoFatura());
        }
    }
    
    private void mudarContratosAbertosParaCancelado(ContratoDTO contratoDTO){        
        Boolean existeContratoEmAberto = repository.existeContratoEmAberto(contratoDTO.getPontoConsumoId());
        if(existeContratoEmAberto){
            List<Contrato> listaContrato = repository.contratoExistenteParaOPontoConsumo(contratoDTO.getPontoConsumoId());
            if(!Objects.isNull(listaContrato) && !listaContrato.isEmpty()){
                for(Contrato contrato: listaContrato){
                    contrato.setContratoSituacao(ContratoSituacaoEnum.CANCELADO);
                    super.save(contrato);
                }
            }
        }
    }

   public List<Contrato> obterListaContratoPorCliente(Long idCliente) throws NegocioException {
        return repository.obterListaContratoPorCliente(idCliente);
   }

   public ContratoPontoConsumo consultarContratoPontoConsumoPorPontoConsumo(Long pontoConsumoId)
           throws NegocioException {

      Long situacaoId = ContratoSituacaoEnum.ATIVO.getId();

      List<ContratoPontoConsumo> listaContatoPontoConsumo = new ArrayList<>();
              //contratoPontoConsumoRepository.consultarContratoPontoConsumoPorPontoConsumoRecente(pontoConsumoId,
              //situacaoId);

      ContratoPontoConsumo retorno = null;
      if (!listaContatoPontoConsumo.isEmpty()) {
         retorno = ToolUtil.primeiroElemento(listaContatoPontoConsumo);
      }
      return retorno;
   }

    public Contrato consultarContratoPontoConsumoPorPontoConsumoAtivoOuMaisRecente(Fatura fatura)
            throws NegocioException {
        Contrato resultadoDeConsultaPontoConsumoPorPontoConsumoAtivoOuMaisRecente = null;
        if (fatura.getContratoAtual() != null) {
            resultadoDeConsultaPontoConsumoPorPontoConsumoAtivoOuMaisRecente = fatura.getContratoAtual();
        } else {
            if (fatura.getPontoConsumo() != null) {
                ContratoPontoConsumo contratoAtivoPontoConsumo = obterContratoAtivoPontoConsumo(
                        fatura.getPontoConsumo());
                if (contratoAtivoPontoConsumo != null) {
                    resultadoDeConsultaPontoConsumoPorPontoConsumoAtivoOuMaisRecente = contratoAtivoPontoConsumo
                            .getContrato();
                } else {
                    List<ContratoPontoConsumo> listacontratoPontoConsumo =
                            consultarContratoPontoConsumoPorPontoConsumoAntesDataEmissao(
                            fatura);
                    if (CollectionUtils.isNotEmpty(listacontratoPontoConsumo)) {
                        ContratoPontoConsumo contratoPontoConsumo =
                                ToolUtil.primeiroElemento(listacontratoPontoConsumo);
                        resultadoDeConsultaPontoConsumoPorPontoConsumoAtivoOuMaisRecente = contratoPontoConsumo
                                .getContrato();
                    }
                }
            }
        }
        return resultadoDeConsultaPontoConsumoPorPontoConsumoAtivoOuMaisRecente;
    }

    public ContratoPontoConsumo obterContratoAtivoPontoConsumo(PontoConsumo pontoConsumo) {
        ContratoPontoConsumo contratoPontoConsumo = new ContratoPontoConsumo();
        ContratoSituacaoEnum contratosituacao = ContratoSituacaoEnum.ATIVO;

        contratoPontoConsumo = repository.obterContratoAtivoPontoConsumo(pontoConsumo.getId(), contratosituacao);

        return contratoPontoConsumo;
    }

    public List<ContratoPontoConsumo> consultarContratoPontoConsumoPorPontoConsumoAntesDataEmissao(Fatura fatura)
            throws NegocioException {

        List<ContratoPontoConsumo> contratosPontoConsumo = new ArrayList<>();

        contratosPontoConsumo =
                repository.consultarContratoPontoConsumoPorPontoConsumoAntesDataEmissao(fatura.getPontoConsumo().getId(), fatura.getDataEmissao());

        return contratosPontoConsumo;
    }

    public BigDecimal fatorCorrecaoIndiceFinanceiro(IndiceFinanceiro indiceFinanceiro, LocalDate dataReferenciaInicial,
                                                    LocalDate dataReferenciaFinal) {

        int incrementoRazaoDia = 0;
        int incrementoRazaoMes = 0;
        LocalDate dataIterator = null;
        LocalDate dataFinalIterator = null;

        if (indiceFinanceiro.getMensal()) {
            incrementoRazaoDia = 0;
            incrementoRazaoMes = 1;
            dataIterator = ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaInicial);
            dataFinalIterator = ToolDate.decrementarDiaMes(ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaFinal),
                    0, 1);
        } else {
            incrementoRazaoDia = 1;
            incrementoRazaoMes = 0;
            dataIterator = ToolDate.incrementarDiaMes(dataReferenciaInicial, 1, 0);
            dataFinalIterator = dataReferenciaFinal;
        }

        List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro = listaValorNominalIndiceFinanceiro(
                indiceFinanceiro, dataIterator, dataFinalIterator);
        Map<String, IndiceFinanceiroValorNominal> mapaIndiceFinanceiroValorNominal = ToolUtil
                .trasformarParaMapaPorHashString(listaValorNominalIndiceFinanceiro);

        BigDecimal fator = BigDecimal.ONE;
        BigDecimal ultimoValorNominalIndiceFinanceiroComputado = null;

        while (ToolDate.menorOuIgualQue(dataIterator, dataFinalIterator)) {
            BigDecimal valorNominal = BigDecimal.ZERO;
            String stringDataIterator = ToolDate.converterDataParaString(dataIterator, false);

            if (mapaIndiceFinanceiroValorNominal.containsKey(stringDataIterator)) {
                valorNominal = mapaIndiceFinanceiroValorNominal.get(stringDataIterator).getValor();
                ultimoValorNominalIndiceFinanceiroComputado = valorNominal;
            } else {
                if (ultimoValorNominalIndiceFinanceiroComputado != null) {
                    valorNominal = ultimoValorNominalIndiceFinanceiroComputado;
                } else {
                    LocalDate dataMaximaBusca = ToolDate.decrementarDiaMes(dataIterator, incrementoRazaoDia,
                            incrementoRazaoMes);
                    IndiceFinanceiroValorNominal indiceFinanceiroValorNominal = obterIndiceFinanceiroValorNominalMaisRecente(
                            indiceFinanceiro, dataMaximaBusca);
                    if (indiceFinanceiroValorNominal != null) {
                        valorNominal = indiceFinanceiroValorNominal.getValor();
                    }
                    ultimoValorNominalIndiceFinanceiroComputado = valorNominal;
                }
            }
            fator = fator.multiply(valorNominal.add(BigDecimal.ONE));
            dataIterator = ToolDate.incrementarDiaMes(dataIterator, incrementoRazaoDia, incrementoRazaoMes);
        }

        return fator;
    }


    public List<BigDecimal> fatorCorrecaoIndiceFinanceiro(IndiceFinanceiro indiceFinanceiro,
                                                          LocalDate dataReferenciaInicial,
                                                          LocalDate dataReferenciaFinal, Boolean mesAnterior,
                                                          EntidadeConteudo tipoCalculo) {
        int incrementoRazaoDia = 0;
        int incrementoRazaoMes = 0;
        LocalDate dataIterator = null;
        LocalDate dataFinalIterator = null;
        List<BigDecimal> fator = new ArrayList<>();
        BigDecimal valorNominal = BigDecimal.ZERO;
        String stringDataIterator = null;

        //if (!mesAnterior){
        if (tipoCalculo.getId() == 912L){
            Map<Integer, Integer> diasPorMes = ToolDate.diasPorMesPeriodo(dataReferenciaInicial, dataReferenciaFinal);
            Calendar cal =  Calendar.getInstance();
            if (indiceFinanceiro.getMensal()) {
                incrementoRazaoDia = 0;
                incrementoRazaoMes = 1;
                dataIterator = ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaInicial);
                dataFinalIterator = ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaFinal);
            } else {
                incrementoRazaoDia = 1;
                incrementoRazaoMes = 0;
                dataIterator = ToolDate.incrementarDiaMes(dataReferenciaInicial, 1, 0);
                dataFinalIterator = dataReferenciaFinal;
            }

            List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro = listaValorNominalIndiceFinanceiro(
                    indiceFinanceiro, dataIterator, dataFinalIterator);
            Map<String, IndiceFinanceiroValorNominal> mapaIndiceFinanceiroValorNominal = ToolUtil
                    .trasformarParaMapaPorHashString(listaValorNominalIndiceFinanceiro);

            BigDecimal ultimoValorNominalIndiceFinanceiroComputado = null;

            while (ToolDate.menorOuIgualQue(dataIterator, dataFinalIterator)) {
                stringDataIterator = ToolDate.converterDataParaString(dataIterator, false);

                if (mapaIndiceFinanceiroValorNominal.containsKey(stringDataIterator)) {
                    valorNominal = mapaIndiceFinanceiroValorNominal.get(stringDataIterator).getValor();
                    ultimoValorNominalIndiceFinanceiroComputado = valorNominal;
                } else {
                    if (ultimoValorNominalIndiceFinanceiroComputado != null) {
                        valorNominal = ultimoValorNominalIndiceFinanceiroComputado;
                    } else {
                        LocalDate dataMaximaBusca = ToolDate.decrementarDiaMes(dataIterator, incrementoRazaoDia,
                                incrementoRazaoMes);
                        IndiceFinanceiroValorNominal indiceFinanceiroValorNominal = obterIndiceFinanceiroValorNominalMaisRecente(
                                indiceFinanceiro, dataMaximaBusca);
                        if (indiceFinanceiroValorNominal != null) {
                            valorNominal = indiceFinanceiroValorNominal.getValor();
                        }
                        ultimoValorNominalIndiceFinanceiroComputado = valorNominal;
                    }
                }

                Integer mes = dataIterator.getMonthValue();
                if(valorNominal.compareTo(BigDecimal.ZERO) == 1){
                    fator.add(valorNominal.divide(BigDecimal.valueOf(30), 10, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(diasPorMes.get(mes))));
                }
                dataIterator = ToolDate.incrementarDiaMes(dataIterator, incrementoRazaoDia, incrementoRazaoMes);
            }
        }


        //if (mesAnterior) {
        if (tipoCalculo.getId() == 913L) {
            Integer dias = ToolDate.diferencaDiasEntreDatas(dataReferenciaInicial, dataReferenciaFinal);
            dataIterator = ToolDate.decrementarDiaMes(ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaInicial), 0,1);
            stringDataIterator = ToolDate.converterDataParaString(dataIterator, false);
            List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro = listaValorNominalIndiceFinanceiro(
                    indiceFinanceiro, dataIterator, dataIterator);
            Map<String, IndiceFinanceiroValorNominal> mapaIndiceFinanceiroValorNominal = ToolUtil
                    .trasformarParaMapaPorHashString(listaValorNominalIndiceFinanceiro);

            if (mapaIndiceFinanceiroValorNominal.containsKey(stringDataIterator)) {
                valorNominal = mapaIndiceFinanceiroValorNominal.get(stringDataIterator).getValor();
                valorNominal = valorNominal.divide(BigDecimal.valueOf(30), 10, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(dias));
            }
            if(valorNominal.compareTo(BigDecimal.ZERO) == 1){
                fator.add(valorNominal);
            }
        }

        if (tipoCalculo.getId() == 939L) {
            Integer dias = ToolDate.diferencaDiasEntreDatas(dataReferenciaInicial, dataReferenciaFinal);
            dataIterator = ToolDate.gerarDataSemHoraPrimeiroDiaMes(dataReferenciaFinal);
            stringDataIterator = ToolDate.converterDataParaString(dataIterator, false);
            List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro = listaValorNominalIndiceFinanceiro(
                    indiceFinanceiro, dataIterator, dataIterator);
            if(listaValorNominalIndiceFinanceiro.size() < 1){
                dataIterator = ToolDate.decrementarDiaMes(dataIterator, 0,1);
                stringDataIterator = ToolDate.converterDataParaString(dataIterator, false);
                listaValorNominalIndiceFinanceiro = listaValorNominalIndiceFinanceiro(
                        indiceFinanceiro, dataIterator, dataIterator);
            }
            Map<String, IndiceFinanceiroValorNominal> mapaIndiceFinanceiroValorNominal = ToolUtil
                    .trasformarParaMapaPorHashString(listaValorNominalIndiceFinanceiro);

            if (mapaIndiceFinanceiroValorNominal.containsKey(stringDataIterator)) {
                valorNominal = mapaIndiceFinanceiroValorNominal.get(stringDataIterator).getValor();
                valorNominal = valorNominal.divide(BigDecimal.valueOf(30), 10, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(dias));
            }
            if(valorNominal.compareTo(BigDecimal.ZERO) == 1){
                fator.add(valorNominal);
            }
        }

        return fator;
    }

    private List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro(
            IndiceFinanceiro indiceFinanceiro, LocalDate dataReferenciaInicial, LocalDate dataReferenciaFinal) {

        return this.listaValorNominalIndiceFinanceiro(indiceFinanceiro, dataReferenciaInicial, dataReferenciaFinal,
                true);
    }

    private List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro(
            IndiceFinanceiro indiceFinanceiro, LocalDate dataReferenciaInicial, LocalDate dataReferenciaFinal,
            boolean ordenacaoAscendente) {

        List<IndiceFinanceiroValorNominal> indicesFinanceiros = new ArrayList<>();

        indicesFinanceiros = repository.listaValorNominalIndiceFinanceiro(indiceFinanceiro.getId(),
                dataReferenciaInicial, dataReferenciaFinal);

        return indicesFinanceiros;
    }

    private IndiceFinanceiroValorNominal obterIndiceFinanceiroValorNominalMaisRecente(IndiceFinanceiro indiceFinanceiro,
                                                                                      LocalDate dataReferenciaFinal) {

        return ToolUtil.primeiroElemento(
                this.listaValorNominalIndiceFinanceiro(indiceFinanceiro, null, dataReferenciaFinal, false));
    }
    
    public List<ComboDTO> findContratosFiltradosCade(Long contratoId, Long segmentoPaiId){
        return this.repository.findContratosFiltradosCade(contratoId, segmentoPaiId);
    }
}