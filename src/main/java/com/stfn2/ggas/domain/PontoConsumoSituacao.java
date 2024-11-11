package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PontoConsumoSituacaoDTO;
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
@Table(name = "PONTO_CONSUMO_SITUACAO")
public class PontoConsumoSituacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "POCS_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_POCS")
	@SequenceGenerator(name = "SQ_POCS", sequenceName = "SQ_POCS_CD", allocationSize = 1)
	private Long id;

	@Column(name = "POCS_DS")
	private String descricao;

	@Column(name = "POCS_IN_FATURAVEL")
	private Boolean faturavel;

	@Column(name = "POCS_IN_LEITURA")
	private Boolean leitura;
	
	@Column(name = "POCS_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "POCS_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "POCS_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public PontoConsumoSituacao(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, PontoConsumoSituacaoDTO.class);
	}
}
