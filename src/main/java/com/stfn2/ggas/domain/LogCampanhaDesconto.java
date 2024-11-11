package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.LogCampanhaDescontoDTO;
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
@Table(name = "CPGAS_LOG_CAMPANHA_DESCONTO")
public class LogCampanhaDesconto extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LCAD_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LCAD")
	@SequenceGenerator(name = "SQ_LCAD", sequenceName = "SQ_LCAD_CD", allocationSize = 1)
	private Long id;
        
        @Column(name = "DESCRICAO_ACAO", nullable = false)
	private String descricaoAcao;
        
        @Column(name = "DATA_EXECUCAO", nullable = false)
	private LocalDateTime dataExecucao = LocalDateTime.now();
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "CADE_CD")
        private CampanhaDesconto campanhaDesconto;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "CADV_CD")
        private CampanhaDescontoVincular campanhaDescontoVincular;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "USSI_CD", nullable = false)
        private User usuario;
		

	public LogCampanhaDesconto(Long id) {
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
		return MapperImpl.parseObject(this, LogCampanhaDescontoDTO.class);
	}

        @Override
        protected Integer getVersao() {
            return null;
        }

        @Override
        protected LocalDateTime getUltimaAlteracao() {
            return null;
        }

        @Override
        protected Boolean getHabilitado() {
            return null;
        }
}