package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ConstanteSistemaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONSTANTE_SISTEMA")
public class ConstanteSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COST_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COST")
	@SequenceGenerator(name = "SQ_COST", sequenceName = "SQ_COST_CD", allocationSize = 1)
	private Long id;

	@Column(name = "COST_NM")
	private String nome;

	@Column(name = "COST_DS")
	private String descricao;

	@Column(name = "COST_VL")
	private String valor;

	@Column(name = "COST_CD_CLASSE")
	private Integer classe;

	@Column(name = "COST_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COST_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COST_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();


	public ConstanteSistema(Long id) {
		super(id);
		this.id = id;
	}

	public Integer getValorInteger() throws NegocioException {
		String valorInteger = this.getValor();
		if (StringUtils.isNotEmpty(valorInteger)) {
			try {
				return Integer.valueOf(valorInteger);
			} catch (NumberFormatException e) {
				throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE,
						getId());
			}
		} else {
			throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE,
					getId());
		}
	}

	public Long getValorLong() throws NegocioException {
		String valorLong = this.getValor();
		if (StringUtils.isNotEmpty(valorLong)) {
			try {
				return Long.valueOf(valorLong);
			} catch (NumberFormatException e) {
				throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE, getId());
			}
		} else {
			throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE, getId());
		}
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
		return MapperImpl.parseObject(this, ConstanteSistemaDTO.class);
	}
}