package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BoletoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_BOLETO")
public class Boleto extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CHAVE_PRIMARIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPGAS_BOLETO")
	@SequenceGenerator(name = "SQ_CPGAS_BOLETO", sequenceName = "SQ_CPGAS_BOLETO_CD", allocationSize = 1)
	private Long id;

	@Column(name = "ENDERECO_CLIENTE")
	private String enderecoCliente;

	@Column(name = "NOME_CLIENTE_BOLETO")
	private String nomeClienteBoleto;

	@Column(name = "VALOR_TOTAL_BOLETO")
	private String valorTotalBoleto;

	@Column(name = "OUTRA_DEDUCOES")
	private String outraDeducoes;

	@Column(name = "IMAGEM_LOGO_BANCO")
	private String imagemLogoBanco;

	@Column(name = "MORA_MULTA")
	private String moraMulta;

	@Column(name = "DATA_DOCUMENTO")
	private String dataDocumento;

	@Column(name = "DATA_PROCESSAMENTO")
	private String dataProcessamento;

	@Column(name = "NUMERO_CODIGO")
	private String numeroCodigo;

	@Column(name = "DESCONTOS_ABATIMENTOS")
	private String descontosAbatimentos;

	@Column(name = "OUTROS_ACRESCIMOS")
	private String outrosAcrescimos;

	@Column(name = "VALOR_DOCUMENTO")
	private String valorDocumento;

	@Column(name = "CEDENTE")
	private String cedente;

	@Column(name = "NUMERO_BANCO")
	private String numeroBanco;

	@Column(name = "CODIGO_BARRA")
	private String codigoBarra;

	@Column(name = "AGENCIA_CODIGO")
	private String agenciaCodigo;

	@Column(name = "ESPECIE_TITULO")
	private String especieTitulo;

	@Column(name = "NUMERO_DOC_PAGAMENTO")
	private String numeroDocPagamento;

	@Column(name = "LOCAL_PAGAMENTO")
	private String localPagamento;

	@Column(name = "CARTEIRA")
	private String carteira;

	@Column(name = "NOSSO_NUMERO")
	private String nossoNumero;

	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;

	@Column(name = "DATA_VENCIMENTO")
	private String dataVencimento;

	@Column(name = "NOME_BANCO")
	private String nomeBanco;

	@Column(name = "BENEFICIARIO")
	private String beneficiario;

	@Column(name = "CODIGO_CLIENTE")
	private String codigoCliente;

	@Column(name = "CPF_CNPJ")
	private String cpfCnpj;

	public Boleto(Map<String, Object> map) {
		updateBoleto(map);
	}

	private String mapToString(Map<String, Object> map, String key) {
		return (String) map.get(key);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> mapBoleto = new HashMap<>();
		mapBoleto.put("enderecoCliente", enderecoCliente);
		mapBoleto.put("nomeClienteBoleto", nomeClienteBoleto);
		mapBoleto.put("valorTotalBoleto", valorTotalBoleto);
		mapBoleto.put("outraDeducoes", outraDeducoes);
		mapBoleto.put("imagemLogoBanco", imagemLogoBanco);
		mapBoleto.put("moraMulta", moraMulta);
		mapBoleto.put("dataDocumento", dataDocumento);
		mapBoleto.put("dataProcessamento", dataProcessamento);
		mapBoleto.put("numeroCodigo", numeroCodigo);
		mapBoleto.put("descontosAbatimentos", descontosAbatimentos);
		mapBoleto.put("outrosAcrescimos", outrosAcrescimos);
		mapBoleto.put("valorDocumento", valorDocumento);
		mapBoleto.put("cedente", cedente);
		mapBoleto.put("numeroBanco", numeroBanco);
		mapBoleto.put("codigoBarra", codigoBarra);
		mapBoleto.put("agenciaCodigo", agenciaCodigo);
		mapBoleto.put("especieTitulo", especieTitulo);
		mapBoleto.put("numeroDocPagamento", numeroDocPagamento);
		mapBoleto.put("localPagamento", localPagamento);
		mapBoleto.put("carteira", carteira);
		mapBoleto.put("nossoNumero", nossoNumero);
		mapBoleto.put("numeroDocumento", numeroDocumento);
		mapBoleto.put("dataVencimento", dataVencimento);
		mapBoleto.put("nomeBanco", nomeBanco);
		mapBoleto.put("beneficiario", beneficiario);
		mapBoleto.put("codigoCliente", codigoCliente);
		mapBoleto.put("cpfCnpj", cpfCnpj);
		return mapBoleto;
	}

	public void updateBoleto(Map<String, Object> map) {
		setEnderecoCliente(mapToString(map, "enderecoCliente"));
		setNomeClienteBoleto(mapToString(map, "nomeClienteBoleto"));
		setValorTotalBoleto(mapToString(map, "valorTotalBoleto"));
		setOutraDeducoes(mapToString(map, "outraDeducoes"));
		setImagemLogoBanco(mapToString(map, "imagemLogoBanco"));
		setMoraMulta(mapToString(map, "moraMulta"));
		setDataDocumento(mapToString(map, "dataDocumento"));
		setDataProcessamento(mapToString(map, "dataProcessamento"));
		setNumeroCodigo(mapToString(map, "numeroCodigo"));
		setDescontosAbatimentos(mapToString(map, "descontosAbatimentos"));
		setOutrosAcrescimos(mapToString(map, "outrosAcrescimos"));
		setValorDocumento(mapToString(map, "valorDocumento"));
		setCedente(mapToString(map, "cedente"));
		setNumeroBanco(mapToString(map, "numeroBanco"));
		setCodigoBarra(mapToString(map, "codigoBarra"));
		setAgenciaCodigo(mapToString(map, "agenciaCodigo"));
		setEspecieTitulo(mapToString(map, "especieTitulo"));
		setNumeroDocPagamento(mapToString(map, "numeroDocPagamento"));
		setLocalPagamento(mapToString(map, "localPagamento"));
		setCarteira(mapToString(map, "carteira"));
		setNossoNumero(mapToString(map, "nossoNumero"));
		setNumeroDocumento(mapToString(map, "numeroDocumento"));
		setDataVencimento(mapToString(map, "dataVencimento"));
		setNomeBanco(mapToString(map, "nomeBanco"));
		setBeneficiario("Companhia Paranaense de Gás – Compagas");
		setCodigoCliente(mapToString(map, "codigoCliente"));
		setCpfCnpj(mapToString(map, "cpfCnpj"));
	}

	@Transient
	private Boolean habilitado = true;

	@Transient
	private Integer versao = 0;

	@Transient
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Boleto(Long id) {
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
		return MapperImpl.parseObject(this, BoletoDTO.class);
	}
}