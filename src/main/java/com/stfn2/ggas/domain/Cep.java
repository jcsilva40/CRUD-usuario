package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CepDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CEP")
public class Cep extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CEP_CD")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CEP")
	//@SequenceGenerator(name = "SQ_CEP", sequenceName = "SQ_CEP_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
	private Municipio municipio;

	@Column(name = "CEP_NR")
	private String numeroCep;

	@Column(name = "CEP_NM_BAIRRO")
	private String bairro;

	@Column(name = "CEP_NM_LOGRADOURO")
	private String logradouro;

	@Column(name = "CEP_SG_UF")
	private String uf;

	@Column(name = "CEP_DS_TIPO_LOGRADOURO")
	private String tipoLogradouro;

	@Column(name = "CEP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CEP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CEP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Cep(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return getNumeroCep();
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CepDTO.class);
	}




	/*






	@Column(name = "CEP_DS_INTERVALO_NUMERACAO")
	private String intervaloNumeracao;

	*/

		
}
