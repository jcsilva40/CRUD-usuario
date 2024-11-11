package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FUNC_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNC")
	@SequenceGenerator(name = "SQ_FUNC", sequenceName = "SQ_FUNC_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPR_CD", referencedColumnName = "EMPR_CD")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNOR_CD", referencedColumnName = "UNOR_CD")
	private UnidadeOrganizacional unidadeOrganizacional;

	@Column(name = "FUNC_NM")
	private String descricao;

	@Column(name = "FUNC_DS_CARGO")
	private String cargo;

	@Column(name = "FUNC_DS_EMAIL")
	private String email;

	@Column(name = "FUNC_CD_DDD")
	private Boolean ddd;

	@Column(name = "FUNC_NR_FONE")
	private String numeroTelefone;

	@Column(name = "FUNC_NR_MATRICULA")
	private String numeroMatricula;

	@Column(name = "FUNC_IN_FISCAL")
	private Boolean fiscal;

	@Column(name = "FUNC_IN_VENDEDOR")
	private Boolean vendedor;

	@Column(name = "FUNC_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FUNC_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FUNC_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Funcionario(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FuncionarioDTO.class);
	}
}
