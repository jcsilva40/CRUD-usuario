package com.stfn2.ggas.config.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PAPEL_SISTEMA")
public class Permission implements GrantedAuthority, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PASI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PASI")
	@SequenceGenerator(name = "SQ_PASSI", sequenceName = "SQ_PASI_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "PASI_DS")
	private String description;

	@Column(name = "PASI_DS_COMPLEMENTO")
	private String detalhe;

	@Column(name = "PASI_IN_ADMIN")
	private Boolean admin;

	@Column(name = "PASI_NR_VERSAO")
	private Integer versao;

	@Column(name = "PASI_IN_USO")
	private Boolean habilitado;

	@Column(name = "PASI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Permission() {}

	@Override
	public String getAuthority() {
		return this.description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Permission that = (Permission) o;

		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}