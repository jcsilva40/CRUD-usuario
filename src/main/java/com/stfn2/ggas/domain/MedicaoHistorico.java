package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedicaoHistoricoDTO;
import com.stfn2.ggas.domain.enumTypes.LeituraSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.LeituraSituacaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDICAO_HISTORICO")
public class MedicaoHistorico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEHI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEHI")
	@SequenceGenerator(name = "SQ_MEHI", sequenceName = "SQ_MEHI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "LEIT_CD", referencedColumnName = "LEIT_CD")
	private Leiturista leiturista;

	@ManyToOne
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@ManyToOne
	@JoinColumn(name = "MEIN_CD", referencedColumnName = "MEIN_CD")
	private MedidorInstalacao medidorInstalacao;

	@Convert(converter = LeituraSituacaoConverter.class)
	@Column(name = "LESI_CD")
	private LeituraSituacaoEnum leituraSituacao;

	@Column(name = "MEHI_MD_LEITURA_INFORMADA")
	private BigDecimal leituraInformada;

	@Column(name = "MEHI_TM_LEITURA_INFORMADA")
	private LocalDate dataLeituraInformada;

	@Column(name = "MEHI_MD_LEITURA_FATURADA")
	private BigDecimal leituraFaturada;

	@Column(name = "MEHI_TM_LEITURA_FATURADA")
	private LocalDate dataLeituraFaturada;

	@Column(name = "MEHI_MD_LEITURA_ANTERIOR")
	private BigDecimal leituraAnterior;

	@Column(name = "MEHI_TM_LEITURA_ANTERIOR")
	private LocalDate dataLeituraAnterior;

	@Column(name = "MEHI_MD_LEITURA_CORRETOR")
	private BigDecimal leituraCorretor;

	@Column(name = "MEHI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEHI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEHI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "MEHI_DT_AM_LEITURA")
	private Long anoMes;

	@Column(name = "MEHI_NR_CICLO")
	private Long ciclo;

	@Column(name = "MEHI_MD_CONSUMO_CORRETOR")
	private BigDecimal consumoCorretor;

	@Column(name = "MEHI_MD_CONSUMO_MEDIDOR")
	private BigDecimal consumoMedidor;

	@Column(name = "MEHI_NR_FATOR_PTZ")
	private BigDecimal fatorPTZ;

	public MedicaoHistorico(Long id) {
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
		return MapperImpl.parseObject(this, MedicaoHistoricoDTO.class);
	}
}