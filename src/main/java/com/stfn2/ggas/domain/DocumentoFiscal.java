package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoFiscalDTO;
import com.stfn2.ggas.domain.enumTypes.StatusNfeEnum;
import com.stfn2.ggas.domain.enumTypes.TipoEmissaoNfeEnum;
import com.stfn2.ggas.domain.enumTypes.TipoFaturamentoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.StatusNfeConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoEmissaoNfeConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoFaturamentoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoOperacaoConverter;
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
@Table(name = "DOCUMENTO_FISCAL")
public class DocumentoFiscal extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOFI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOFI")
	@SequenceGenerator(name = "SQ_DOFI", sequenceName = "SQ_DOFI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@ManyToOne
	@JoinColumn(name = "FATU_CD_ORIGEM", referencedColumnName = "FATU_CD")
	private Fatura faturaOrigemDevolucao;

	@ManyToOne
	@JoinColumn(name = "NAOC_CD", referencedColumnName = "NAOC_CD")
	private NaturezaOperacaoCfop naturezaOperacaoCFOP;

	@ManyToOne
	@JoinColumn(name = "SERI_CD", referencedColumnName = "SERI_CD")
	private Serie serie;

	@Column(name = "ENCO_CD_TIPO_OPERACAO")
	@Convert(converter = TipoOperacaoConverter.class)
	private TipoOperacaoEnum tipoOperacao;

	@Column(name = "ENCO_CD_TIPO_FATURAMENTO")
	@Convert(converter = TipoFaturamentoConverter.class)
	private TipoFaturamentoEnum tipoFaturamento;

	@Column(name = "ENCO_CD_TIPO_EMISSAO_NFE")
	@Convert(converter = TipoEmissaoNfeConverter.class)
	private TipoEmissaoNfeEnum tipoEmissaoNfe;

	@Column(name = "ENCO_CD_STATUS_NFE")
	@Convert(converter = StatusNfeConverter.class)
	private StatusNfeEnum statusNfe;

	@Column(name="DOFI_NR")
	private Long numero;

	@Column(name="DOFI_DS_SERIE")
	private String descricaoSerie;

	@Column(name="DOFI_CD_ANTT")
	private Integer codigoANTT;

	@Column(name="DOFI_VL_TOTAL")
	private BigDecimal valorTotal;

	@Column(name="DOFI_NM_CLIENTE")
	private String nomeCliente;

	@Column(name="DOFI_NR_CPF_CNPJ")
	private String cpfCpnj;

	@Column(name="DOFI_NR_INSC_ESTADUAL")
	private String inscricaoEstadual;

	@Column(name="DOFI_NR_RG")
	private String rg;

	@Column(name="DOFI_DS_ENDERECO")
	private String endereco;

	@Column(name="DOFI_DS_COMPLEMENTO")
	private String complemento;

	@Column(name="DOFI_NM_BAIRRO")
	private String bairro;

	@Column(name="DOFI_NR_CEP")
	private String cep;

	@Column(name="DOFI_NM_MUNICIPIO")
	private String municipio;

	@Column(name="DOFI_SG_UF")
	private String uf;

	@Column(name="DOFI_DT_APRESENTACAO")
	private LocalDate apresentacao;

	@Column(name="DOFI_DT_EMISSAO")
	private LocalDate dataEmissao;

	@Transient
	private String descricaoPontoConsumo;

	@Column(name="DOFI_DS_MENSAGEM")
	private String mensagem;

	@Column(name="DOFI_DS_PLACA_VEICULO")
	private String placaVeiculo;

	@Column(name="DOFI_SG_UF_PLACA")
	private String ufPlaca;

	@Column(name="DOFI_NM_TRANSPORTADOR")
	private String nomeTransportador;

	@Column(name="DOFI_NR_CPF_CNPJ_TRANSPORT")
	private String cpfCNPJTransportadora;

	@Column(name="DOFI_DS_MUNICIPIO_TRANSPORT")
	private String municipioTransportadora;

	@Column(name="DOFI_SG_UF_TRANSPORTADORA")
	private String ufTransportadora;

	@Column(name="DOFI_NR_INSC_ESTADUAL_TRANSP")
	private String inscEstadualTransportadora;

	@Column(name="DOFI_NR_INSC_ESTADUAL_SUBS")
	private String inscricaoEstadualSubs;

	@Column(name="DOFI_TM_HORA_SAIDA")
	private LocalDateTime horaSaida;

	@Column(name="DOFI_DS_ENDERECO_TRANSPORT")
	private String enderecoTransportadora;

	@Column(name="DOFI_NR_CHAVE_ACESSO_NFE")
	private String chaveAcesso;

	@Column(name="DOFI_NR_PROTOCOLO")
	private String numeroProtocolo;

	@Column(name="DOFI_IN_NORMAL_ELETRONICA")
	private Integer indicadorTipoNota;

	@Column(name="DOFI_DS_ARQUIVO_XML")
	private byte[] arquivoXml;

	@Column(name="DOFI_IN_EMISSAO_CLIENTE")
	private Integer emissaoCliente;

	@Column(name = "DOFI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DOFI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DOFI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();


	public DocumentoFiscal(Long id) {
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
		return MapperImpl.parseObject(this, DocumentoFiscalDTO.class);
	}
}