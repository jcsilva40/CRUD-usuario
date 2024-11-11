package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.TipoContatoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTATO_TIPO")
public class TipoContato extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COTI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTI")
	@SequenceGenerator(name = "SQ_COTI", sequenceName = "SQ_COTI_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "COTI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COTI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COTI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "COTI_DS")
	private String descricao;

	//todo gilmar verificar necessidade mudar para enum ou remover,
	// Indica se Ã© pessoa fisica = 1, pessoa juridica = 2  ou ambos = 0
	@Column(name = "COTI_IN_TIPO_PESSOA")
	private Integer pessoaTipo = 0;

	public TipoContato(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, TipoContatoDTO.class);
	}
}