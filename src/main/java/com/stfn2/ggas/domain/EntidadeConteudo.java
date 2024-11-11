package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENTIDADE_CONTEUDO")
public class EntidadeConteudo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENCO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENCO")
	@SequenceGenerator(name = "SQ_ENCO", sequenceName = "SQ_ENCO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ENCL_CD", referencedColumnName = "ENCL_CD")
	private EntidadeClasse entidadeClasse;

	@Column(name = "ENCO_DS", nullable = false, length = 20)
	private String descricao;

	@Column(name = "ENCO_DS_ABREVIADO", nullable = true, length = 6)
	private String abreviado;

	@Column(name = "ENCO_IN_PADRAO", length = 1)
	private Boolean padrao;

	@Column(name = "ENCO_NR_CODIGO", length = 10)
	private String codigo;

	public EntidadeConteudo(Long id) {
		super(id);
		this.id = id;
	}

	@Column(name = "ENCO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ENCO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ENCO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Override
	public BaseDTO convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
