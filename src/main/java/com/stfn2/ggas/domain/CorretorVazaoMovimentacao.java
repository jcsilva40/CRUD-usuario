package com.stfn2.ggas.domain;

import com.stfn2.ggas.domain.enumTypes.converter.MedidorLocalArmazenagemConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorMotivoMovimentacaoConverter;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CorretorVazaoMovimentacaoDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorLocalArmazenagemEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorMotivoMovimentacaoEnum;
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
@Table(name = "CORRETOR_VAZAO_MOVIMENTACAO")
public class CorretorVazaoMovimentacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COMV_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMV")
	@SequenceGenerator(name = "SQ_COMV", sequenceName = "SQ_COMV_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COVA_CD", referencedColumnName = "COVA_CD")
	private CorretorVazao corretorVazao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNC_CD", referencedColumnName = "FUNC_CD")
	private Funcionario funcionario;

	@Convert(converter = MedidorMotivoMovimentacaoConverter.class)
	@Column(name = "MEMM_CD")
	private MedidorMotivoMovimentacaoEnum motivoMovimentacaoMedidor;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD_ORIGEM")
	private MedidorLocalArmazenagemEnum localArmazenagemOrigem;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD_DESTINO")
	private MedidorLocalArmazenagemEnum localArmazenagemDestino;

	@Column(name = "COMV_DS_PARECER")
	private String descricaoParecer;

	@Column(name = "COMV_DT")
	private LocalDateTime dataMovimento;

	@Column(name = "COMV_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COMV_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COMV_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CorretorVazaoMovimentacao(Long id) {
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
		return MapperImpl.parseObject(this, CorretorVazaoMovimentacaoDTO.class);
	}
}