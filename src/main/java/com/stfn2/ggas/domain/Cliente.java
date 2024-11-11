package com.stfn2.ggas.domain;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteDTO;
import com.stfn2.ggas.tools.ToolStr;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CLIE_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIE")
    @SequenceGenerator(name = "SQ_CLIE", sequenceName = "SQ_CLIE_CD", allocationSize = 1)
    private Long id;

    @Column(name = "CLIE_NM")
    private String nome;

    @Column(name = "CLIE_NM_FANTASIA")
    private String nomeFantasia;

    @Column(name = "CLIE_NM_ABREVIADO")
    private String nomeAbreviado;

    @Column(name = "CLIE_DS_EMAIL_PRINCIPAL")
    private String emailPrincipal;

    /*
    	Trata-se do imposto sobre o rendimento das empresas, recolhido pela Receita Federal e cobrado para
    	todas as pessoas jurídicas, mensurado conforme o regime tributário da organização.
     */
    @Column(name = "CLIE_IN_REGIME_ISENTO")
    private Short regimeRecolhimento;

    @Column(name = "CLIE_NR_INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;

    @Column(name = "CLIE_NR_INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

    @Column(name = "CLIE_IN_DENEGADO")
    @Deprecated
    private Boolean indicadorDenegado = false;

    @Column(name = "CLIE_IN_PUBLICO")
    private Boolean clientePublico;

    @Column(name = "CLSI_CD")
    private Long clienteSituacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
    private Segmento segmento;

    @Column(name = "CLIE_NR_CPF")
    private String cpf;

    @Column(name = "CLIE_NR_CNPJ")
    private String cnpj;

    @Column(name = "CLIE_NR_RG")
    private String rg;

    @Column(name = "CLIE_NM_MAE")
    private String nomeMae;

    @Column(name = "NOME_CONJUGE")
    private String nomeConjuge;

    @Column(name = "CLIE_DT_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CLIE_IE_FATUARMENTO")
    private String ieFaturamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PESE_CD", referencedColumnName = "PESE_CD")
    private PessoaSexo sexo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NACI_CD", referencedColumnName = "NACI_CD")
    private Nacionalidade nacionalidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORER_CD", referencedColumnName = "ORER_CD")
    private OrgaoExpedidor orgaoEmissorRG;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNFE_CD", referencedColumnName = "UNFE_CD")
    private UnidadeFederacao ufEmissaoRG;

    @OrderBy("nome")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ClienteContato> contatos = new ArrayList<>();


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ClienteEndereco> enderecos = new ArrayList<>();

    @Override
    public String getDescricaoField(){
        return "nome";
    }

    @Override
    public String getDescricao() {
        return getNome();
    }

    @Override
    public void setDescricao(String descricao) { }

    @Column(name = "CLIE_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "CLIE_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "CLIE_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    //public AtividadeEconomica atividadeEconomica;

    //public ClienteSituacao situacao;

    //public TipoCliente tipoCliente;

    @Column(name = "CLTI_CD")
    private Long tipoCliente;

    public Cliente(Long id) {
        super(id);
        this.id = id;
    }

    public String getCnpjFormatado() {
        if (this.cnpj == null) {
            return this.cnpj;
        } else {
            return ToolStr.formatarValor(this.cnpj, Constantes.MASCARA_CNPJ);
        }
    }

    public String getCpfFormatado() {
        if (this.cpf == null) {
            return this.cpf;
        } else {
            return ToolStr.formatarValor(this.cpf, Constantes.MASCARA_CPF);
        }
    }

    public ClienteEndereco getEnderecoPrincipal() {

        ClienteEndereco enderecoPrincipal = null;
        if (this.getEnderecos() != null && !this.getEnderecos().isEmpty()) {
            for (ClienteEndereco endereco : this.getEnderecos()) {
                if (endereco.getCorrespondencia() != null
                        && endereco.getCorrespondencia()) {
                    enderecoPrincipal = endereco;
                    break;
                }
            }
        }
        if(enderecoPrincipal == null && this.getEnderecos() != null && !this.getEnderecos().isEmpty()) {
            enderecoPrincipal = this.getEnderecos().iterator().next();
        }
        return enderecoPrincipal;
    }

    public String getNumeroCpfCnpj() {
        String retorno = "";
        if(!StringUtils.isEmpty(this.getCpf())) {
            retorno = this.getCpf();
        } else {
            if(!StringUtils.isEmpty(this.getCnpj())) {
                retorno = this.getCnpj();
            }
        }
        return retorno;
    }

    @Override
    public BaseDTO convert() {
        ClienteDTO dto = MapperImpl.parseObject(this, ClienteDTO.class);
        dto.setCpfCnpj(this.cnpj != null ? this.cnpj : this.cpf);
        return dto;
    }
}
