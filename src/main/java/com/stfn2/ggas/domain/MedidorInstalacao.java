package com.stfn2.ggas.domain;

import com.stfn2.ggas.domain.enumTypes.converter.MedidorLocalInstalacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorProtecaoConverter;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorInstalacaoDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorLocalInstalacaoEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorProtecaoEnum;
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
@Table(name = "MEDIDOR_INSTALACAO")
public class MedidorInstalacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEIN_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEIN")
	@SequenceGenerator(name = "SQ_MEIN", sequenceName = "SQ_MEIN_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDI_CD", referencedColumnName = "MEDI_CD")
	private Medidor medidor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNC_CD", referencedColumnName = "FUNC_CD")
	private Funcionario funcionario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COVA_CD", referencedColumnName = "COVA_CD")
	private CorretorVazao corretorVazao;

	@OneToMany(mappedBy = "medidorInstalacao", fetch = FetchType.LAZY)
	private List<MedidorOperacaoHistorico> listaOpHistoricoMedidor = new ArrayList<>();

	@Convert(converter = MedidorLocalInstalacaoConverter.class)
	@Column(name = "MELI_CD")
	private MedidorLocalInstalacaoEnum localInstalacao;

	@Convert(converter = MedidorProtecaoConverter.class)
	@Column(name = "MEPR_CD")
	private MedidorProtecaoEnum medidorProtecaoEnum;

	@Column(name = "MEIN_DT")
	private LocalDate data;

	@Column(name = "MEIN_DT_COVA")
	private LocalDate dataCorretorVazao;

	@Column(name = "MEIN_MD_LEITURA")
	private BigDecimal leitura;

	@Column(name = "MEIN_DT_ATIVACAO")
	private LocalDate dataAtivacao;

	@Column(name = "MEIN_MD_LEITURA_ATIVACAO")
	private BigDecimal leituraAtivacao;

	@Column(name = "MEDI_FATOR_CORRECAO")
	private BigDecimal fatorCorrecao;

	@Column(name = "MEDI_CONSUMO_FINAL")
	private BigDecimal consumoFinal;

	@Column(name = "MEDI_SEQUENCIA_LEITURA")
	private Integer sequenciaLeitura;

	@Column(name = "MEIN_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEIN_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEIN_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorInstalacao(Long id) {
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
		return MapperImpl.parseObject(this, MedidorInstalacaoDTO.class);
	}
}