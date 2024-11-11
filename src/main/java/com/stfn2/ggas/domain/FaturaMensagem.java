package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaMensagemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURA_MENSAGEM")
public class FaturaMensagem extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CPGAS_FATURA_MENSAGEM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPGAS_FATURA_MENSAGEM")
	@SequenceGenerator(name = "SQ_CPGAS_FATURA_MENSAGEM", sequenceName = "SQ_CPGAS_FATURA_MENSAGEM_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "CPGAS_FATURA_MENSAGEM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CPGAS_FATURA_MENSAGEM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CPGAS_FATURA_MENSAGEM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "CPGAS_FATURA_MENSAGEM_DESCRICAO")
	private String descricao;

	public FaturaMensagem(Long id) {
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
		return MapperImpl.parseObject(this, FaturaMensagemDTO.class);
	}
}