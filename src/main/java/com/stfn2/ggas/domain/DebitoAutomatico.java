package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DebitoAutomaticoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEBITO_AUTOMATICO")
public class DebitoAutomatico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEAU_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEAU")
	@SequenceGenerator(name = "SQ_DEAU", sequenceName = "SQ_DEAU_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "DEAU_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DEAU_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DEAU_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

        @Column(name = "DEAU_CD_AGENCIA")
	private String agencia;

        @Column(name = "DEAU_CD_CONTA")
	private String conta;	

        @Column(name = "DEAU_DT_INCLUSAO")
	private LocalDate dataInclusao;

        @Column(name = "DEAU_DT_EXCLUSAO")
	private LocalDate dataExclusao;	
	
        @Column(name = "DEAU_NR_CLIENTE")
	private String numeroCliente;
	
        @Column(name = "DEAU_VENCIMENTO")
	private String vencimento;
	
        @Column(name = "DEAU_FATU_VL")
	private BigDecimal valorFatura;
	        	
        @Column(name = "DEAU_REF_FATURA")
	private String referencia;
        
        @Transient
        private String numeroAgendamento;
        
        @Transient
	private LocalDate dataOpcao;
        
        @ManyToOne
        @JoinColumn(name = "ARRE_CD", nullable = false)
	private Arrecadador arrecadador;
        
        @ManyToOne
        @JoinColumn(name = "CLDA_CD", nullable = false)
        private ClienteDebitoAutomatico clienteDebitoAutomatico;
        
        private PontoConsumo pontoConsumo;

	private Cliente cliente;        
        
	public DebitoAutomatico(Long id) {
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
		return MapperImpl.parseObject(this, DebitoAutomaticoDTO.class);
	}
}