package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ArrecadadorCarteiraCobrancaDTO;
import com.stfn2.ggas.domain.enumTypes.CodigoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.CodigoCarteiraCobrancaConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoCarteiraCobrancaConverter;
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
@Table(name = "ARRECADADOR_CARTEIRA_COBRANCA")
public class ArrecadadorCarteiraCobranca extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ARCA_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARCA")
   @SequenceGenerator(name = "SQ_ARCA", sequenceName = "SQ_ARCA_CD", allocationSize = 1)
   private Long id;

   @OneToMany(mappedBy = "arrecadadorCarteiraCobranca", cascade = CascadeType.MERGE, orphanRemoval = true)
   List<ArrecadadorContratoConvenio> arrecadadorContratoConvenio = new ArrayList<>();

   @Column(name = "ARCA_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "ARCA_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "ARCA_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "ARCA_DS", nullable = false)
   private String descricao;

   @Column(name = "ARCA_NR", nullable = false)
   private Integer numero;

   @Column(name = "ARCA_IN_FAIXA_NOSSO_NUMERO", nullable = false)
   private Boolean indicadorFaixaNossoNumero = true;

   @Column(name = "ARCA_IN_NOSSO_NUMERO_LIVRE", nullable = false)
   private Boolean indicadorNossoNumeroLivre = true;

   @Column(name = "ARCA_IN_EMISSAO", nullable = false)
   private Boolean indicadorEmissao = false;

   @Column(name = "ARCA_IN_PROTESTO", nullable = false)
   private Boolean indicadorProtesto = false;

   @Column(name = "ARCA_IN_ENTREGA", nullable = false)
   private Boolean indicadorEntrega = false;

   @Column(name = "ARCA_DS_LAYOUT_FATURA", nullable = false)
	 private String arquivoLayoutFatura;

   @ManyToOne
   @JoinColumn(name = "ARRE_CD", referencedColumnName = "ARRE_CD")
   private Arrecadador arrecadador;

   @Column(name = "ENCO_CD_CODIGO_CARTEIRA")
   @Convert(converter = CodigoCarteiraCobrancaConverter.class)
   private CodigoCarteiraCobrancaEnum codigoCarteira; //Valor padrão 42, mas pode ser 887

   @Column(name = "ENCO_CD_TIPO_CARTEIRA")
   @Convert(converter = TipoCarteiraCobrancaConverter.class)
   private TipoCarteiraCobrancaEnum tipoCarteira; //Valor padrão 38

   public ArrecadadorCarteiraCobranca(Long id) {
      super(id);
      this.id = id;
   }

//   @Override
//   public String getDescricao() {
//      return null;
//   }
//
//   @Override
//   public void setDescricao(String descricao) {
//
//   }

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, ArrecadadorCarteiraCobrancaDTO.class);
   }
}