package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemSegmentoDTO;
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
@Table(name = "FATURAMENTO_MENSAGEM_SEGMENTO")
public class FaturamentoMensagemSegmento extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAMS_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAMS")
	@SequenceGenerator(name = "SQ_FAMS", sequenceName = "SQ_FAMS_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAME_CD", referencedColumnName = "FAME_CD")
	private FaturamentoMensagem faturamentoMensagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RAAT_CD", referencedColumnName = "RAAT_CD")
	private RamoAtividade ramoAtividade;
	
	@Column(name = "FAMS_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAMS_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAMS_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoMensagemSegmento(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoMensagemSegmentoDTO.class);
	}
}