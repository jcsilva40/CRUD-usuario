package com.stfn2.ggas.domain;

import com.stfn2.ggas.domain.enumTypes.converter.ConsumoComposicaoTipoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorSituacaoConverter;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorMotivoOperacaoDTO;
import com.stfn2.ggas.domain.enumTypes.ConsumoComposicaoTipo;
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
@Table(name = "MEDIDOR_MOTIVO_OPERACAO")
public class MedidorMotivoOperacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEMP")
	@SequenceGenerator(name = "SQ_MEMP", sequenceName = "SQ_MEMP_CD", allocationSize = 1)
	private Long id;

	@Convert(converter = ConsumoComposicaoTipoConverter.class)
	@Column(name = "COCT_CD")
	private ConsumoComposicaoTipo consumoComposicaoTipo;

	@Convert(converter = MedidorSituacaoConverter.class)
	@Column(name = "MESI_CD_ATUAL")
	private MedidorSituacaoEnum medidorSituacaoEnum;

	@Column(name = "MEMP_DS")
	private String descricao;

	@Column(name = "MEMP_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "MEMP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEMP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEMP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorMotivoOperacao(Long id) {
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
		return MapperImpl.parseObject(this, MedidorMotivoOperacaoDTO.class);
	}
}