package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTO extends BaseDTO {
        private Long id;
        
        private Long contratoPontoConsumoId;
	
	private Boolean habilitado = true;

	private Integer versao = 0;

	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
        
        //Aba Dados
        private Long pontoConsumoId;
        
        private Long clienteAssinaturaId;
        
        private String numeroContrato;
        
        private Integer numero;
        
        private Integer anoContrato;
        
        private Long tipoVinculoContratoId;
        
        private LocalDate dataAssinatura;
        
        private LocalDate dataLiberacaoGas;
        
        private LocalDate dataVencimentoObrigacoes;
        
        private LocalDateTime dataCriacao;
        
        //Aba financeiro
        private Boolean indicadorParticipanteECartas;
        
        //falta achar faturamentoAgrupamento
        
        private Long tipoAgrupamentoId;
        
        private Long formaCobrancaId;
        
        private Long arrecadadorContratoConvenioId;
        
        private Long bancoId;
        
        private String agencia;

	private String contaCorrente;
        
        private String numeroDebitoAutomatico;
        
        private Boolean indicadorMulta;
        
        private BigDecimal percentualMulta;

        private Boolean indicadorJuros;
        
        private BigDecimal percentualJuros;
        
        private Long indiceFinanceiroId;

        private Long tipoCalculoCorrecaoMonetariaId;
        
        private Long garantiaFinanceiraId;

        private String descricaoGarantiaFinanceira;
        
        private BigDecimal valorGarantiaFinanceira;
        
        //Aba Dados Tecnico, estão em contratoPontoConsumo        
        private BigDecimal medidaVazaoMaximaInstantanea;
        
        private Long unidadeVazaoMaximaInstantaneaId;
        
        private BigDecimal medidaPressao;
        
        /*private Integer quantidadeAnosParadaCliente;

        private Integer quantidadeTotalParadaCliente;

        private Integer quantidadeMaximaAnoParadaCliente;

        private Integer diasAntecedentesParadaCliente;

        private Integer diasConsecutivosParadaCliente;

        private Integer quantidadeAnosParadaCDL;

        private Integer quantidadeTotalParadaCDL;

        private Integer quantidadeMaximaAnoParadaCDL;

        private Integer diasAntecedentesParadaCDL;
        
        private Integer diasConsecutivosParadaCDL;*/ //Não usar por enquanto e provavélmente não será usado        
        
        private Long regimeConsumoId;
        
        private BigDecimal medidaFornecimentoMaxDiaria;
        
        private Long unidadeFornecimentoMaxDiariaId;
        
        private BigDecimal medidaFornecimentoMinMensal;
        
        private Long unidadeFornecimentoMinMensalId;
        
        private Long localAmostragemPCSId;
        
        private Long pcsIntervaloId;
        
        //Aba Faturamento
        private Boolean envioFaturaFisica;
        
        private ContratoPontoConsumoEnderecoFaturaDTO enderecoFatura;
        
        private String emailEntrega;
        
        private Long contratoCompraId;
        
        private Long periodicidadeCompraId;
        
        private Boolean emiteNotaFiscalEletronica;
        
        private Boolean emitirFaturaComNfe;
        
        private List<ContratoPontoConsumoItemFaturamentoDTO> listaContratoItemFaturamento;
        
        //Aba Responsabilidade
        private Long clienteResponsabilidadeId;
        
        private Long responsabilidadeId;
        
        private LocalDate dataRelacaoInicio;
                
        //@JsonSerialize(using = ContratoSituacaoSerializer.class)
        private ContratoSituacaoEnum contratoSituacao;

    	public ContratoDTO (Contrato entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}