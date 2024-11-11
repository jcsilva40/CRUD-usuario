package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FavoritosDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO_FAVORITOS")
public class Favoritos extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USFA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USFA")
	@SequenceGenerator(name = "SQ_USFA", sequenceName = "SQ_USFA_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "USFA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "USFA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "USFA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Favoritos(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return null;
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FavoritosDTO.class);
	}
}