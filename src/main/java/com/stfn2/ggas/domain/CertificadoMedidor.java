package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CertificadoMedidorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_CERTIFICADO_MEDIDOR")
public class CertificadoMedidor extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CHAVE_PRIMARIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPGAS_CERTIFICADO_MEDIDOR")
	@SequenceGenerator(name = "SQ_CPGAS_CERTIFICADO_MEDIDOR", sequenceName = "SQ_CPGAS_CERTIFICADO_MEDIDOR_CD", allocationSize = 1)
	private Long id;

	@Column(name = "CERTIFICADO")
	private String descricao;

	@Column(name = "CALIBRACAO")
	private LocalDate calibracao;

	@Column(name = "ARQUIVO")
	private byte[] arquivo;

	@Column(name = "HABILITADO")
	private Boolean habilitado = true;

	@Column(name = "VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CertificadoMedidor(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CertificadoMedidorDTO.class);
	}
}