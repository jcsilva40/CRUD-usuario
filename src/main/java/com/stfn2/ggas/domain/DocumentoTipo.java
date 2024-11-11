package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoTipoDTO;
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
@Table(name = "DOCUMENTO_TIPO")
public class DocumentoTipo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOTI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOTI")
	@SequenceGenerator(name = "SQ_DOTI", sequenceName = "SQ_DOTI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "DOTI_DS")
	private String descricao;

	@Column(name = "DOTI_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "DOTI_IN_PAGAVEL")
	private Boolean pagavel;

	@Column(name = "DOTI_IN_CODIGO_BARRAS")
	private Boolean codigoBarras;

	@Column(name = "DOTI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DOTI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DOTI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public DocumentoTipo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, DocumentoTipoDTO.class);
	}
}