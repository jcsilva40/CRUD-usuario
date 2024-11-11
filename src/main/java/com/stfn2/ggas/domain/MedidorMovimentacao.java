package com.stfn2.ggas.domain;

import com.stfn2.ggas.domain.enumTypes.converter.MedidorLocalArmazenagemConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorMotivoMovimentacaoConverter;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorMovimentacaoDTO;
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
@Table(name = "MEDIDOR_MOVIMENTACAO")
public class MedidorMovimentacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEMO")
	@SequenceGenerator(name = "SQ_MEMO", sequenceName = "SQ_MEMO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDI_CD", referencedColumnName = "MEDI_CD")
	private Medidor medidor;

    //Não apresenta dados no banco
//	@ManyToOne
//	@JoinColumn(name = "MELA_CD_ORIGEM", referencedColumnName = "MELA_CD")
//	private MedidorLocalArmazenagem localArmazenagemOrigem;
//
//	@ManyToOne
//	@JoinColumn(name = "MELA_CD_DESTINO", referencedColumnName = "MELA_CD")
//	private MedidorLocalArmazenagem localArmazenagemDestino;

	//Não apresenta dados no banco
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNC_CD", referencedColumnName = "FUNC_CD")
	private Funcionario funcionario;

	@Convert(converter = MedidorMotivoMovimentacaoConverter.class)
	@Column(name = "MEMM_CD")
	private MedidorMotivoMovimentacaoEnum medidorMovimentacaoMotivo;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD_ORIGEM")
	private MedidorLocalArmazenagemEnum localArmazenagemOrigem;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD_DESTINO")
	private MedidorLocalArmazenagemEnum localArmazenagemDestino;

	@Column(name = "MEMO_DT")
	private LocalDateTime dataMovimento;

	@Column(name = "MEMO_DS_PARECER")
	private String descricaoParecer;

	@Column(name = "MEMO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEMO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEMO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorMovimentacao(Long id) {
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
		return MapperImpl.parseObject(this, MedidorMovimentacaoDTO.class);
	}
}