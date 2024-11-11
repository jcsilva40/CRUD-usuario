package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SegmentoAmostragemPCSDTO;
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
@Table(name = "SEGMENTO_AMOSTRAGEM_PCS")
public class SegmentoAmostragemPCS extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEAP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SEAP_CD")
	@SequenceGenerator(name = "SQ_SEAP_CD", sequenceName = "SQ_SEAP_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_LOCAL_AMOSTRAGEM_PCS", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo localAmostragem;

	@Transient
	private Boolean habilitado = true;

	@Column(name="SEAP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name="SEAP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public SegmentoAmostragemPCS(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return "";
	}

	@Override
	public void setDescricao(String descricao) {
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, SegmentoAmostragemPCSDTO.class);
	}

}
