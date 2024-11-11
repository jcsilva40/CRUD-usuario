package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.OperacaoSistemaDTO;
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
@Table(name = "OPERACAO_SISTEMA")
public class OperacaoSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OPSI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OPSI")
	@SequenceGenerator(name = "SQ_OPSI", sequenceName = "SQ_OPSI_CD", allocationSize = 1)
	private Long id;

	/*@ManyToOne
	@JoinColumn(name = "MENU_CD", referencedColumnName = "MENU_CD")
	private Menu menu;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOSI_CD", referencedColumnName = "MOSI_CD")
	private ModuloSistema moduloSistema;

	@Column(name = "OPSI_DS")
	private String descricao;

	@Column(name = "OPSI_IN_AUDITAVEL")
	private Boolean auditavel = true;

	@Column(name = "OPSI_IN_EXIBIR")
	private Boolean exibir = true;

	@Column(name = "OPSI_NR_TIPO")
	private Integer tipoOperacao = 0;

	@Column(name = "CD_RS")
	private String rs;

	@Column(name = "OPSI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "OPSI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "OPSI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public OperacaoSistema(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, OperacaoSistemaDTO.class);
	}
}