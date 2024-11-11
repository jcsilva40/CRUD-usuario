package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CorretorVazaoDTO;
import com.stfn2.ggas.domain.enumTypes.*;
import com.stfn2.ggas.domain.enumTypes.converter.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORRETOR_VAZAO")
public class CorretorVazao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COVA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COVA")
	@SequenceGenerator(name = "SQ_COVA", sequenceName = "SQ_COVA_CD", allocationSize = 1)
	private Long id;

	@OneToMany(mappedBy = "corretorVazao", fetch = FetchType.LAZY)
	private List<CorretorVazaoMovimentacao> movimentacoes = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COTM_CD", referencedColumnName = "COTM_CD")
	private CorretorTempMaxTransdutor temperaturaMaximaTransdutor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COPT_CD", referencedColumnName = "COPT_CD")
	private CorretorPressMaxTransdutor pressaoMaximaTransdutor;

	@Convert(converter = MostradorTipoConverter.class)
	@Column(name = "COVA_IN_TIPO_MOSTRADOR")
	private MostradorTipoEnum codigo;

	@Convert(converter = CorretorMarcaConverter.class)
	@Column(name = "COMA_CD")
	private CorretorMarcaEnum corretorMarcaEnum;

	@Convert(converter = TransdutorTemperaturaTipoConverter.class)
	@Column(name = "TRTT_CD")
	private TransdutorTemperaturaTipoEnum transdutorTemperaturaTipo;

	@Convert(converter = TransdutorPressaoTipoConverter.class)
	@Column(name = "TRPT_CD")
	private TransdutorPressaoTipoEnum transdutorPressaoTipo;

	@Convert(converter = ProtocoloComunicacaoDadoConverter.class)
	@Column(name = "PRCD_CD")
	private ProtocoloComunicacaoDado protocoloComunicacaoDado;

	@Convert(converter = CorretorModeloConverter.class)
	@Column(name = "COMD_CD")
	private CorretorModeloEnum corretorModeloEnum;

	@Convert(converter = MedidorSituacaoConverter.class)
	@Column(name = "MESI_CD")
	private MedidorSituacaoEnum medidorSituacaoEnum;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD")
	private MedidorLocalArmazenagemEnum medidorLocalArmazenagemEnum;

	@Column(name = "COVA_NR_SERIE")
	private String descricao;

	@Column(name = "COVA_NR_ANO_FABRICACAO")
	private Integer numeroAnoFabricacao;

	@Column(name = "COVA_IN_CORRECAO_PRESSAO")
	private Boolean correcaoPressao;

	@Column(name = "COVA_IN_CORRECAO_TEMPERATURA")
	private Boolean correcaoTemperatura;

	@Column(name = "COVA_IN_CONTROLE_VAZAO")
	private Boolean controleVazao;

	@Column(name = "COVA_IN_LINEARIZACAO_FATOR_K")
	private Boolean linearizacaoFatorK;

	@Column(name = "COVA_NR_DIGITO")
	private Integer numeroDigitos;

	@Column(name = "COVA_NR_TOMBO")
	private String tombamento;

	@Column(name = "COVA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COVA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COVA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CorretorVazao(Long id) {
		super(id);
		this.id = id;
	}

//	@Override
//	public String getDescricao() {
//		return null;
//	}
//
//	@Override
//	public void setDescricao(String descricao) {
//
//	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CorretorVazaoDTO.class);
	}
}