package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ParametroSistemaDTO;
import com.stfn2.ggas.domain.enumTypes.TipoParametroSistemaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoParametroSistemaConverter;
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
@Table(name = "PARAMETRO_SISTEMA")
public class ParametroSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PMSI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PMSI")
	@SequenceGenerator(name = "SQ_PMSI", sequenceName = "SQ_PMSI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "PMSI_CD_PARAMETRO")
	private String codigo;

	@Column(name = "PMSI_DS_PARAMETRO")
	private String Descricao;

	@Column(name = "PMSI_VL_PARAMETRO")
	private String valor;

	@Column(name = "PMSI_DS_COMPLEMENTO")
	private String classeEntidade;

	@Column(name = "PMSI_CD_TIPO_PARAMETRO")
	@Convert(converter = TipoParametroSistemaConverter.class)
	private TipoParametroSistemaEnum tipoParametroSistema;

	@Column(name = "PMSI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "PMSI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "PMSI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();


	public ParametroSistema(Long id) {
		super(id);
		this.id = id;
	}

	public Integer getValorInteger() throws NegocioException {
		String valor = this.getValor();
		if (StringUtils.isNotEmpty(valor)) {
			try {
				return Integer.valueOf(valor);
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
		String valor = this.getValor();
		if (StringUtils.isNotEmpty(valor)) {
			try {
				return Long.valueOf(valor);
			} catch (NumberFormatException e) {
				throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE, getId());
			}
		} else {
			throw new NegocioException(Constantes.ERRO_NEGOCIO_BUSCAR_CONSTANTE, getId());
		}
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, ParametroSistemaDTO.class);
	}
}