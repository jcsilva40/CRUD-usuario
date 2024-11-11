package com.stfn2.ggas.domain;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RotaCronogramaDTO;
import com.stfn2.ggas.tools.ToolDate;
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
@Table(name = "ROTA_CRONOGRAMA")
public class RotaCronograma extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROCR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROCR")
	@SequenceGenerator(name = "SQ_ROCR", sequenceName = "SQ_ROCR_CD", allocationSize = 1)
	private Long id;

	@Column(name = "ROCR_DT_PREVISTA")
	private LocalDate dataPrevista;

	@Column(name = "ROCR_DT_REALIZADA")
	private LocalDate dataRealizada;

	@Column(name = "ROCR_DT_AM_REFERENCIA")
	private Integer anoMesReferencia;

	@Column(name = "ROCR_NR_CICLO")
	private Integer numeroCiclo;

	@ManyToOne
	@JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
	private Rota rota;

	@ManyToOne
	@JoinColumn(name = "FAAC_CD", referencedColumnName = "FAAC_CD")
	private FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
	
	@Column(name = "ROCR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ROCR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ROCR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public RotaCronograma(Long id) {
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

	public String getDataRealizadaFormatada() {
		if(dataRealizada != null) {
			return ToolDate.converterDataParaStringSemHora(dataRealizada, Constantes.FORMATO_DATA_BR);
		} else {
			return String.valueOf("");
		}
	}

	public String getDataPrevistaFormatada() {
		if(dataPrevista != null) {
			return ToolDate.converterDataParaStringSemHora(dataPrevista, Constantes.FORMATO_DATA_BR);
		} else {
			return String.valueOf("");
		}
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, RotaCronogramaDTO.class);
	}
}