package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BIQueryDTO;
import com.stfn2.ggas.domain.enumTypes.TipoQueryBIEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoEntradaBIEnumConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoQueryBIConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_BI_QUERY")
public class BIQuery extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BIQU_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BIQU_CD")
	@SequenceGenerator(name = "SQ_BIQU_CD", sequenceName = "SQ_BIQU_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "NOME")
	private String descricao;

	@Column(name = "NOME_REPORT")
	private String nomeField;

	@Column(name = "MAIN")
	private Boolean principal;

	@Column(name = "QUERY_STR")
	private String query;

	@Column(name = "TIPO_QUERY")
	@Convert(converter = TipoQueryBIConverter.class)
	private TipoQueryBIEnum tipoQuery;

	@OneToMany(mappedBy = "queryPai", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BIQuery> children = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BIJA_CD", referencedColumnName = "BIJA_CD")
	private BIJasper jasper;

	@ManyToOne
	@JoinColumn(name = "BIQU_FATHER_CD", referencedColumnName = "BIQU_CD")
	private BIQuery queryPai;

	public BIQuery(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, BIQueryDTO.class);
	}
}