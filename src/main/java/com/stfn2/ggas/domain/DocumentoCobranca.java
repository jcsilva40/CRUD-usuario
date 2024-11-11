package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaDTO;
import com.stfn2.ggas.domain.enumTypes.CobrancaDebitoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.CobrancaDebitoSituacaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTO_COBRANCA")
public class DocumentoCobranca extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOCO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCO")
	@SequenceGenerator(name = "SQ_DOCO", sequenceName = "SQ_DOCO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "DOTI_CD", referencedColumnName = "DOTI_CD")
	private DocumentoTipo documentoTipo;

	@Column(name = "CODS_CD")
	@Convert(converter = CobrancaDebitoSituacaoConverter.class)
	private CobrancaDebitoSituacaoEnum cobrancaDebitoSituacao;

	@JsonManagedReference
	@OneToMany(mappedBy = "documentoCobranca", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoCobrancaItem> itens = new ArrayList<>();

	@Column(name = "DOCO_DT_EMISSAO")
	private LocalDate dataEmissao;

	@Column(name = "DOCO_DT_VENCIMENTO")
	private LocalDate dataVencimento;

	@Column(name = "DOCO_NR_SEQUENCIAL")
	private Integer sequencial;

	@Column(name = "DOCO_NR_SEQUENCIAL_IMPRESSAO")
	private Integer sequencialImpressao;

	@Column(name = "DOCO_NR_NOSSO_NUMERO")
	private Long nossoNumero;

	@Column(name = "DOCO_VL_TOTAL")
	private BigDecimal valorTotal;

	@Column(name = "DOCO_NR_CD_BARRAS")
	private String codigoBarras;

	@Column(name = "DOCO_NR_DIGITAVEL")
	private String linhaDigitavel;
	
	@Column(name = "DOCO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DOCO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DOCO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public DocumentoCobranca(Long id) {
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
		return MapperImpl.parseObject(this, DocumentoCobrancaDTO.class);
	}
}