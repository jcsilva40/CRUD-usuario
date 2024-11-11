package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.Menu;
import com.stfn2.ggas.domain.dtos.MenuDTO;
import com.stfn2.ggas.domain.dtos.filter.MenuFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MenuBasicDTO;
import com.stfn2.ggas.repositories.MenuRepository;
import com.stfn2.ggas.services.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/menu")
@Tag(name="Menu", description="EndPoints para gerenciamento de Menu")
public class MenuController extends BaseController<Menu, MenuDTO, MenuBasicDTO, MenuFilterDTO,
		MenuRepository, MenuService> {
}