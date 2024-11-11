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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_MENU")
public class Menu extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CPME_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPME")
    @SequenceGenerator(name = "SQ_CPME", sequenceName = "SQ_CPME_CD", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPME_CD_PAI", referencedColumnName = "CPME_CD")
    private Menu menuPai;
    
    @OneToMany(mappedBy = "menuPai", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordem ASC")
    private List<Menu> subMenus;

    @Column(name = "CPME_DS")
    private String descricao;

    @Column(name = "CPME_URL")
    private String url;

    @Column(name = "CPME_ICON")
    private String icon;

    @Column(name = "CPME_NR_ORDEM")
    private Integer ordem = 0;

    @Column(name = "CPME_IN_EXIBIR_SEM_FILHOS")
    private Boolean exibirFilhos = true;

    @Column(name = "CPME_IN_USO")
    private Boolean habilitado = true;

    public Menu(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public BaseDTO convert() {
        return MapperImpl.parseObject(this, MenuDTO.class);
    }

    @Override
    protected Integer getVersao() {
        return null;
    }

    @Override
    protected LocalDateTime getUltimaAlteracao() {
        return null;
    }
}
