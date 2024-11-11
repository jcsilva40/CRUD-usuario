package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Fatura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaDTO extends BaseDTO {

  private Long id;
	private Long rotaId;
	private Integer anoMes;
	private BigDecimal valorDiferimento;
	private BigDecimal valorConciliado;
	private BigDecimal valorTotal;
	private LocalDate dataEmissao;
	private LocalDate dataVencimento;
	private LocalDateTime dataRevisao;
	private LocalDateTime dataCancelamento;
	private String motivoCancelamentoNota;
	private String motivoAlteracaoVencimento;
	private String observacaoNota;
	private String observacaoFaturaComplementar;
	private String periodoCiclo;
	private Integer numeroFatura;
	private Integer ciclo;
	private BigDecimal pressaoAtual;
	private Boolean denegada;
	private Boolean encerramento;
	private Boolean registroPerda;
	private Boolean notificacao;
	private Boolean cobrada;
	private Boolean pdd;
	private Boolean avulso;
	private Boolean exibirAvisoCorte;
	private String clienteLogradouro;
	private String clienteNumeroImovel;
	private String clienteComplementoEndereco;
	private String clienteBairro;
	private String clienteCep;
	private String clienteMunicipio;
	private String clienteUF;
	private String clienteCpfOrCNPJ;
	private String clienteInscricaoEstadual;

	public FaturaDTO (Fatura entity) {
        	super();
    	}
}
