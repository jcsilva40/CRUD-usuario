package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ImovelRamoAtividadeDTO;
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
@Table(name = "IMOVEL_RAMO_ATIVIDADE")
public class ImovelRamoAtividade extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IMRA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMRA")
	@SequenceGenerator(name = "SQ_IMRA", sequenceName = "SQ_IMRA_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
	private Imovel imovel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RAAT_CD", referencedColumnName = "RAAT_CD")
	private RamoAtividade ramoAtividade;

	@Column(name = "IMRA_QN_ECONOMIA")
	private Integer quantidadeUnidadeConsumidoras = 0;
	
	@Column(name = "IMRA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "IMRA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "IMRA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ImovelRamoAtividade(Long id) {
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
		return MapperImpl.parseObject(this, ImovelRamoAtividadeDTO.class);
	}
}