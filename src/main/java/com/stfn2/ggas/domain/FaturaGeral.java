package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaGeralDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURA_GERAL")
public class FaturaGeral extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAGE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAGE")
	@SequenceGenerator(name = "SQ_FAGE", sequenceName = "SQ_FAGE_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD_ATUAL", referencedColumnName = "FATU_CD")
	private Fatura faturaAtual;

	@JsonManagedReference
	@OneToMany(mappedBy = "faturaGeral")
	private List<Recebimento> listaRecebimento = new ArrayList<>();

	@OneToMany(mappedBy = "faturaGeral", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoCobrancaItem> documentoCobrancaItem = new ArrayList<>();
	
	@Column(name = "FAGE_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAGE_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAGE_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaGeral(Long id) {
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
		return MapperImpl.parseObject(this, FaturaGeralDTO.class);
	}
}