package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.HistoricoLegadoDTO;
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
@Table(name = "CPGAS_HISTORICO_LEGADO")
public class HistoricoLegado extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HILE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HILI")
	@SequenceGenerator(name = "SQ_HILI", sequenceName = "SQ_HILI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "HILE_POCN_CD")
	private String pontoConsumo;

	@Column(name = "HILE_NR_NOTA_FICAL")
	private String notaFiscal;

	@Column(name = "HILE_VOLUME_MEDIDO")
	private String volumeMedido;

	@Column(name = "HILE_VALOR")
	private String valor;

	@Column(name = "HILE_VENCIMENTO")
	private String vencimento;

	@Column(name = "HILE_REF_FAT")
	private String referencia;

	@Column(name = "HILE_PAGAMENTO")
	private String dataPagamento;
	
	@Column(name = "HILE_IN_USO")
	private Boolean habilitado = true;

	@Transient
	private Integer versao = 0;

	@Transient
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public HistoricoLegado(Long id) {
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
		return MapperImpl.parseObject(this, HistoricoLegadoDTO.class);
	}
}