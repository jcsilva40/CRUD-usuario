package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BIStefaniniDTO;
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
@Table(name = "CP_BI_STEFANINI")
public class BIStefanini extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BIST_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BIST_CD")
	@SequenceGenerator(name = "SQ_BIST_CD", sequenceName = "SQ_BIST_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "NOME_BI")
	private String descricao;

	@OrderBy("posicao ASC")
	@OneToMany(mappedBy = "bi", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ParametroBI> parametros = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QUERY_CD", referencedColumnName = "BIQU_CD")
	private BIQuery query;

	public BIStefanini(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, BIStefaniniDTO.class);
	}
}