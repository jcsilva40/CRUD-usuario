package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaDadosTecnicosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_FATURA_DADOS_TECNICOS")
public class FaturaDadosTecnicos extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CHAVE_PRIMARIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPGAS_FATURA_DADOS_TECNICOS")
	@SequenceGenerator(name = "SQ_CPGAS_FATURA_DADOS_TECNICOS", sequenceName = "SQ_CPGAS_FATURA_DADOS_TECNICOS_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@Column(name = "PERIODO")
	private String periodo;

	@Column(name = "NUM_MEDIDOR")
	private String numeroMedidor;

	@Column(name = "PRESSAO_ATUAL")
	private BigDecimal pressaoAtual;

	@Column(name = "NUM_LEITURA_ANTERIOR")
	private BigDecimal numeroLeituraAnterior;

	@Column(name = "NUM_LEITURA_ATUAL")
	private BigDecimal numeroLeituraAtual;

	@Column(name = "CONSUMO_MEDIDO")
	private BigDecimal consumoMedido;

	@Column(name = "FATOR_CORRECAO")
	private BigDecimal fatorCorrecao;

	@Column(name = "CONSUMO_FATURADO")
	private BigDecimal consumoFaturado;

	@Column(name = "habilitado")
	private Boolean habilitado = true;

	@Column(name = "VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaDadosTecnicos(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return null;
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturaDadosTecnicosDTO.class);
	}
}