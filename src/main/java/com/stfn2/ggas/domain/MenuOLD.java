package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MenuDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")*/
public class MenuOLD /*extends BaseEntity*/ {
	/*@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MENU_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MENU")
	@SequenceGenerator(name = "SQ_MENU", sequenceName = "SQ_MENU_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MENU_CD_PAI", referencedColumnName = "MENU_CD")
	private Menu menuPai;

	@ManyToOne
	@JoinColumn(name = "RESI_CD", referencedColumnName = "RESI_CD")
	private RecursoSistema recursoSistema;

	@Column(name = "MENU_DS")
	private String descricao;

	@Column(name = "MENU_DS_URL")
	private String descricaoUrl;

	@Column(name = "MENU_NR_ORDEM")
	private Integer ordem = 0;

	@Column(name = "MENU_IN_ALCADA")
	private Boolean alcada;

	@Column(name = "MENU_IN_EXIBIR_SEM_FILHOS")
	private Boolean exibirFilhos = true;

	@Column(name = "MENU_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MENU_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MENU_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Menu(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MenuDTO.class);
	}*/
}