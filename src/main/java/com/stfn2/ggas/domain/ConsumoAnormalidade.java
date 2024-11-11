package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ConsumoAnormalidadeDTO;
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
@Table(name = "CONSUMO_ANORMALIDADE")
public class ConsumoAnormalidade extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COAN_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COAN")
	@SequenceGenerator(name = "SQ_COAN", sequenceName = "SQ_COAN_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "COAN_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COAN_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COAN_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "COAN_DS")
	private String descricao;
        
        @Column(name = "COAN_DS_ABREVIADO")
        private String abreviado;

        @Column(name = "COAN_IN_BLOQUEIA_FATURAMENTO", nullable = false)
	private Boolean bloquearFaturamento;

        @Column(name = "COAN_NR_OCOR_ANORMALIDADE")
	private Long numeroOcorrenciaConsecutivasAnormalidade;

        @Column(name = "COAN_DS_MENSAGEM_CONSUMO")
	private String mensagemConsumo;

        @Column(name = "COAN_DS_REGRA")
	private String regra;

        @Column(name = "COAN_IN_IGNORAR_ACAO_AUTO")
	private Boolean ignorarAcaoAutomatica;

	public ConsumoAnormalidade(Long id) {
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
		return MapperImpl.parseObject(this, ConsumoAnormalidadeDTO.class);
	}
}