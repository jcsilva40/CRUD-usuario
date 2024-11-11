package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.TarifaDTO;
import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoModeloContratoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ItemFaturaConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoModeloContratoConverter;
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
@Table(name = "TARIFA")
public class Tarifa extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TARI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TARI")
	@SequenceGenerator(name = "SQ_TARI", sequenceName = "SQ_TARI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "RAAT_CD", referencedColumnName = "RAAT_CD")
	private RamoAtividade ramoAtividade;

	@ManyToOne
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@JsonManagedReference
	@OneToMany(mappedBy = "tarifa", cascade = CascadeType.ALL)
	private List<TarifaVigencia> vigencias = new ArrayList<>();

	@Column(name = "ENCO_CD_ITEM_FATURA")
	@Convert(converter = ItemFaturaConverter.class)
	private ItemFaturaEnum itemFatura;

	@Column(name = "ENCO_CD_TIPO_CONTRATO")
	@Convert(converter = TipoModeloContratoConverter.class)
	private TipoModeloContratoEnum tipoContrato;

	@Column(name = "TARI_DS")
	private String descricao;

	@Column(name = "TARI_DS_ABREVIADA")
	private String abreviado;

	@Column(name = "TARI_QN_CASA_DECIMAL")
	private Integer casaDecimal;

	@Column(name = "SEFAZ_NM")
	private String nomeSefaz;

	@Column(name = "TARI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "TARI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "TARI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Tarifa(Long id){
		super(id);
		this.id = id;
  }

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, TarifaDTO.class);
	}
}
