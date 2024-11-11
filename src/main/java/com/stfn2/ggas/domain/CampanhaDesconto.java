package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoDTO;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCampanhaDescontoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoDescontoCadeEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ModalidadeMedicaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoCampanhaDescontoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoDescontoCadeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_CAMPANHA_DESCONTO")
public class CampanhaDesconto extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CADE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CADE")
	@SequenceGenerator(name = "SQ_CADE", sequenceName = "SQ_CADE_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "HABILITADO")
	private Boolean habilitado = true;

	@Column(name = "VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "DESCRICAO")
	private String descricao;
        
        @Convert(converter = TipoDescontoCadeConverter.class)
        @Column(name = "TIPO_DESCONTO")
        private TipoDescontoCadeEnum tipoDesconto;
	
        @Column(name = "PORCENTAGEM")
	private BigDecimal porcentagem;
	
        @Column(name = "INICIO_ADESAO")
	private LocalDate inicioAdesao;
	
        @Column(name = "PERIODO_VIGENCIA")
	private Short periodoVigencia;
	
        @Column(name = "ENCERRAMENTO_ADESAO")
	private LocalDate encerramentoAdesao;
	
        @Column(name = "MENSAGEM_FATURA")
	private String mensagemFatura;
	
        @Convert(converter = TipoCampanhaDescontoConverter.class)
        @Column(name = "TIPO_CAMPANHA")
	private TipoCampanhaDescontoEnum tipoCampanha;
        
        /*@ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ENCO_CD_SEGM_CAMPANHA", nullable = false)*/
        /*@Convert(converter = SegmentoCampanhaConverter.class)
        @Column(name = "ENCO_CD_SEGM_CAMPANHA")
        private SegmentoCampanhaEnum segmentoCampanha;*/

        /*@ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ENCO_CD_MOD_MEDICAO")*/
        @Convert(converter = ModalidadeMedicaoConverter.class)
        @Column(name = "ENCO_CD_MOD_MEDICAO")
        private ModalidadeMedicaoEnum modalidadeMedicao;
        
        @Column(name = "VALOR_UNITARIO_POR_VOLUME")
	private BigDecimal valorUnitarioPorVolume;
        
        @Column(name = "QUANTIDADE_VOLUME")
	private Long quantidadePorVolume;

        @ManyToOne
        @JoinColumn(name = "SEGP_CD")
        private SegmentoPai segmentoPai;
        
        @OneToMany(mappedBy = "campanhaDesconto", fetch = FetchType.LAZY)
        private List<CampanhaDescontoVincular> listaVinculoCampanhaDesconto = new ArrayList<>();

	public CampanhaDesconto(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
            this.descricao = descricao;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CampanhaDescontoDTO.class);
	}
}