package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteEnderecoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE_ENDERECO")
public class ClienteEndereco extends BaseEntity {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "CLEN_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLEN")
   @SequenceGenerator(name = "SQ_CLEN", sequenceName = "SQ_CLEN_CD", allocationSize = 1)
   private Long id;

   @JoinColumn(name = "CEP_CD")
   @OneToOne(fetch = FetchType.LAZY)
   private Cep cep;

   @ManyToOne
   @JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
   private Municipio municipio;

   @Column(name = "CLEN_NR")
   private String numero;

   @Column(name = "CLEN_DS_COMPLEMENTO")
   private String complemento;

   @Column(name = "CLEN_IN_PRINCIPAL")
   private Integer indicadorPrincipal;

   @ManyToOne
   @JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
   private Cliente cliente;

   @Column(name = "CLEN_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "CLEN_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "CLEN_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "ENTI_CD")
   private Long tipoEndereco = 2L;

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, ClienteEnderecoDTO.class);
   }

   @Column(name = "CLEN_IN_CORRESPONDENCIA")
   private Boolean correspondencia = false;

   @Column(name = "CLEN_DS_ENDERECO_REFERENCIA")
   private String enderecoReferencia;

    /*
    //legado?


    @Column(name = "CLEN_NR_CAIXA_POSTAL")
    private Integer caixaPostal;

     */

   @Override
   public String getDescricao() {
      return "";
   }

   @Override
   public void setDescricao(String descricao) {
   }

   public ClienteEndereco(Long id) {
      super(id);
      this.id = id;
   }

   public String getEnderecoLogradouro() {

      final StringBuilder enderecoLogradouro = new StringBuilder();

      if (this.getCep() != null && this.getCep().getLogradouro() != null) {
         final String separador;
         if (enderecoLogradouro.length() > 0) {
            separador = " ";
         } else {
            separador = "";
         }
         enderecoLogradouro.append(separador);
         enderecoLogradouro.append(this.getCep().getLogradouro());
      }

      return enderecoLogradouro.toString();
   }

   public String getEnderecoFormatadoRuaNumeroComplemento() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      String logradouro = this.getEnderecoLogradouro();

      if (logradouro != null && !logradouro.isEmpty()) {
         enderecoFormatado.append(logradouro);
      }

      if (this.getNumero() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumero());
      }

      if (this.getComplemento() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getComplemento());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatado() {

      final StringBuilder enderecoFormatado = new StringBuilder();
      String logradouroNumeroComplemento = this.getEnderecoFormatadoRuaNumeroBairro();

      if (logradouroNumeroComplemento != null && !logradouroNumeroComplemento.isEmpty()) {
         enderecoFormatado.append(logradouroNumeroComplemento);
      }

      if (this.getCep() != null && this.getCep().getMunicipio() != null) {

         final String separador;

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());

      } else if (this.getCep() != null && this.getCep().getMunicipio().getDescricao() != null) {

         final String separador;

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (this.getCep().getUf() != null) {

         final String separador;

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());
      }

      if (this.getCep() != null && this.getEnderecoReferencia() != null) {

         final String separador;

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getEnderecoReferencia());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoMunicipioUF() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getMunicipio().getDescricao() != null) {
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());

      } else if (this.getMunicipio() != null) {
         enderecoFormatado.append(this.getMunicipio().getDescricao());

      } else if (this.getCep().getMunicipio() != null) {
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (this.getCep().getUf() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = " - ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());

      } else if (this.getMunicipio() != null && this.getMunicipio().getUf() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = " - ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getMunicipio().getUf().getSigla());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoBairroMunicipioUFCEP() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getBairro() != null) {
         enderecoFormatado.append(this.getCep().getBairro());
      }
      if (this.getCep().getMunicipio() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      } else if (this.getCep().getMunicipio().getDescricao() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (this.getCep().getUf() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = " - ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());
      }
      if (this.getCep().getNumeroCep() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = " CEP ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getNumeroCep());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoMunicipioUFCEP() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getMunicipio().getDescricao() != null) {
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      } else if (this.getCep().getMunicipio() != null) {
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (this.getCep().getUf() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = " - ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());
      }
      if (this.getCep().getNumeroCep() != null) {
         if (enderecoFormatado.length() > 0) {
            separador = " CEP ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getNumeroCep());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoRuaNumeroBairro() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      String logradouro = this.getEnderecoLogradouro();

      if (logradouro != null && !logradouro.isEmpty()) {
         enderecoFormatado.append(logradouro);
      }

      if (this.getNumero() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", N° ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumero());
      }

      if (this.getCep().getBairro() != null && !this.getCep().getBairro().equals("Não Informado")) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getBairro());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoCepCidadeEstado() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getNumeroCep() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = "";
         } else {
            separador = "CEP: ";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getNumeroCep());
      }

      if (this.getCep().getMunicipio() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = " ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());

      } else if (this.getCep().getMunicipio().getDescricao() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = " ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (this.getCep().getUf() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = "-";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoRuaNumeroBairroMunicipioUF() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      String logradouro = this.getEnderecoLogradouro();

      if (logradouro != null && !logradouro.isEmpty()) {
         enderecoFormatado.append(logradouro);
      }

      if (this.getNumero() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", N° ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumero());
      }

      if (this.getCep().getBairro() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getBairro());
      }

      if (!StringUtils.isEmpty(this.getCep().getMunicipio().getDescricao())) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      if (!StringUtils.isEmpty(this.getCep().getUf())) {
         enderecoFormatado.append("-");
         enderecoFormatado.append(this.getCep().getUf());
      }

      return enderecoFormatado.toString();
   }

}
