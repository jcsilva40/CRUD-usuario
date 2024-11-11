package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import com.stfn2.ggas.domain.enumTypes.ImovelOrigemEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelTipoMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelTipoMedicaoGLPEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ImovelOrigemConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ImovelSituacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ImovelTipoMedicaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ImovelTipoMedicaoGLPConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "IMOVEL")
public class Imovel extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IMOV_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMOV")
	@SequenceGenerator(name = "SQ_IMOV", sequenceName = "SQ_IMOV_CD", allocationSize = 1)
	private Long id;

	/*@ManyToOne
	@JoinColumn(name = "QUFA_CD", referencedColumnName = "CUFA_CD")
	private QuadraFace quadraFace;*/

	@Column(name = "QUFA_CD")
	private Long idQuadraFace = 83601572L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
	private Rota rota;

	@Column(name = "IMOV_NM")
	private String descricao;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "IMOV_DS_ENDERECO_REFERENCIA")
	private String endereco;

	@Column(name = "IMOV_DS_LOGRADOURO")
	private String logradouro;

	@Column(name = "IMOV_DS_BAIRRO")
	private String bairro;

	@Column(name = "IMOV_DS_MUNICIPIO")
	private String municipio;

	@Column(name = "IMOV_DS_UF")
	private String uf;

	@Column(name = "IMOV_NR_IMOVEL")
	private String numero;

	@Column(name = "IMOV_DS_COMPLEMENTO")
	private String complementoEndereco;

	@Column(name = "IMOV_NIP")
	private Integer nip;

	@Column(name = "IMOV_ZONA_BLOQUEIO")
	private String zonaBloqueio;

	@Column(name = "IMOV_QN_PONTO_CONSUMO")
	private Integer quantidadePontosConsumo;

	@Column(name = "IMOV_QN_UNIDADE_CONSUMIDORA")
	private Integer quantidadeUnidadesConsumidora;

	@Column(name = "IMOV_QN_APARTAMENTO_ANDAR")
	private Integer apartamentosPorAndar;

	@Column(name = "IMOV_NR_SEQUENCIA_LEITURA")
	private Integer sequenciaLeitura;

	@Column(name = "IMOV_NR_UDAS")
	private Integer numeroUdas;

	@Column(name = "IMOV_DT_ENTREGA")
	private LocalDate dataEntrega;

	@Column(name = "IMOV_TM_DATA_CRIACAO")
	private LocalDate dataCriacao;

	@Column(name = "IMOV_TM_DATA_TESTE_ESTANQUE")
	private LocalDate dataTesteEstanque;

	@Column(name = "IMOV_IN_CONDOMINIO")
	private Boolean condominio = false;

	@Column(name = "IMOV_IN_PORTARIA")
	private Boolean portaria = false;

	@Convert(converter = ImovelSituacaoConverter.class)
	@Column(name = "IMSI_CD")
	private ImovelSituacaoEnum imovelSituacao;

	@Convert(converter = ImovelOrigemConverter.class)
	@Column(name = "IMOV_ORIGEM")
	private ImovelOrigemEnum imovelOrigem;

	@Convert(converter = ImovelTipoMedicaoConverter.class)
	@Column(name = "IMOV_TIPO_MEDICAO")
	private ImovelTipoMedicaoEnum imovelTipoMedicao;

	@Convert(converter = ImovelTipoMedicaoGLPConverter.class)
	@Column(name = "IMOV_TIPO_MEDICAO_GLP")
	private ImovelTipoMedicaoGLPEnum imovelTipoMedicaoGLP;

	@Column(name = "IMOV_IN_USO")
	private Boolean habilitado;

	@Column(name = "IMOV_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "IMOV_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMOV_CD_PAI", referencedColumnName = "IMOV_CD")
	private Imovel imovelPai;

	@OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnidadeConsumidora> unidadesConsumidoras = new ArrayList<>();

	@OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ClienteImovel> clienteImovel = new ArrayList<>();

	@OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PontoConsumo> pontoConsumos = new ArrayList<>();

	public Imovel(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		ImovelDTO dto = MapperImpl.parseObject(this, ImovelDTO.class);


		return dto;
	}
}