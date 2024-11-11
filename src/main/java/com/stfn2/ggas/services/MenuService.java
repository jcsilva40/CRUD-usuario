package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Menu;
import com.stfn2.ggas.domain.dtos.MenuDTO;
import com.stfn2.ggas.domain.dtos.filter.MenuFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MenuBasicDTO;
import com.stfn2.ggas.repositories.MenuRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService extends BaseService<Menu, MenuDTO, MenuBasicDTO, MenuFilterDTO, MenuRepository> {
    @Override
    public List<MenuBasicDTO> findAll() {
        List<Menu> listaMenuHabilitado = this.repository.findAll();
        listaMenuHabilitado = filterEnabled(listaMenuHabilitado);
        List<MenuBasicDTO> dtos = 
                listaMenuHabilitado.stream()
                .map(this::entityToBasicDTO)
                .collect(Collectors.toList());
        return dtos;
    }
    
    private List<Menu> filterEnabled(List<Menu> menus) {
        if (menus == null) {
            return null;
        }
        return menus.stream()
            .filter(Menu::getHabilitado)
            .map(menu -> {
                menu.setSubMenus(filterEnabled(menu.getSubMenus()));
                return menu;
            })
            .collect(Collectors.toList());
    }
}