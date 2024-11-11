package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuBasicDTO extends BaseDTO {

    private Long id;
    private String descricao;
    private String icon;
    private String url;
    private Integer ordem;
    private Boolean habilitado;
    private List<MenuBasicDTO> subMenus;

    public MenuBasicDTO(Menu entity) {
        super();
    }

    @Override
    public Long getId() {
        return this.id;
    }
}