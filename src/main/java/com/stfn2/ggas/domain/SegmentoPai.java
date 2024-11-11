package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SegmentoPaiDTO;
import com.stfn2.ggas.domain.enumTypes.TipoInfoCadeEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoInfoCadeConverter;
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
@Table(name = "CPGAS_SEGMENTO_PAI")
public class SegmentoPai extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEGP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SEGP")
	@SequenceGenerator(name = "SQ_SEGP", sequenceName = "SQ_SEGP_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "SEGP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "SEGP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "SEGP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "SEGP_DS")
	private String descricao;
        
        @Convert(converter = TipoInfoCadeConverter.class)
        @Column(name = "SEGP_TIPO_INFO_CADE")
        private TipoInfoCadeEnum tipoInfoCade;

	public SegmentoPai(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
            this.descricao = descricao;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, SegmentoPaiDTO.class);
	}
}