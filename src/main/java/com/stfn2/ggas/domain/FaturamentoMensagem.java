package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemDTO;
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
@Table(name = "FATURAMENTO_MENSAGEM")
public class FaturamentoMensagem extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAME_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAME")
	@SequenceGenerator(name = "SQ_FAME", sequenceName = "SQ_FAME_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_TIPO_MENSAGEM", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo tipoMensagem;
	/*@ManyToOne
	@JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
	private Imovel imovel;*/

	@Column(name = "FAME_DT_VIGENCIA_INICIO")
	private LocalDateTime dataInicioVigencia;

	@Column(name = "FAME_DT_VIGENCIA_FIM")
	private LocalDateTime dataFimVigencia;

	@Column(name = "FAME_DS")
	private String descricao;
	
	@Column(name = "FAME_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAME_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAME_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoMensagem(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturamentoMensagemDTO.class);
	}
}