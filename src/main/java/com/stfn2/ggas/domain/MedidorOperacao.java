package com.stfn2.ggas.domain;

import com.stfn2.ggas.domain.enumTypes.converter.MedidorSituacaoConverter;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
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
@Table(name = "MEDIDOR_OPERACAO")
public class MedidorOperacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEOP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEOP")
	@SequenceGenerator(name = "SQ_MEOP", sequenceName = "SQ_MEOP_CD", allocationSize = 1)
	private Long id;

	@Convert(converter = MedidorSituacaoConverter.class)
	@Column(name = "MESI_CD")
	private MedidorSituacaoEnum medidorSituacaoEnum;

	@Column(name = "MEOP_DS")
	private String descricao;

	@Column(name = "MEOP_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "MEOP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEOP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEOP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorOperacao(Long id) {
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
		return MapperImpl.parseObject(this, MedidorOperacaoDTO.class);
	}
}