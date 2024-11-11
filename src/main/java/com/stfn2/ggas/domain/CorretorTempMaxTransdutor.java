package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CorretorTempMaxTransdutorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORRETOR_TEMP_MAX_TRANSDUTOR")
public class CorretorTempMaxTransdutor extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COTM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTM")
	@SequenceGenerator(name = "SQ_COTM", sequenceName = "SQ_COTM_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNID_CD", referencedColumnName = "UNID_CD")
	private Unidade unidade;

	@Column(name = "COTM_MD_TEMPERATURA")
	private BigDecimal temperatura;

	@Column(name = "COTM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COTM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COTM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CorretorTempMaxTransdutor(Long id) {
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
		return MapperImpl.parseObject(this, CorretorTempMaxTransdutorDTO.class);
	}
}