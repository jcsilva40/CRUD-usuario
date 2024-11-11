package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.dtos.FaturaItemDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemBasicDTO;
import com.stfn2.ggas.repositories.FaturaItemRepository;
import com.stfn2.ggas.services.FaturaItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturaItem")
@Tag(name="FaturaItem", description="EndPoints para gerenciamento de FaturaItem")
public class FaturaItemController extends BaseController<FaturaItem, FaturaItemDTO, FaturaItemBasicDTO, FaturaItemFilterDTO,
		FaturaItemRepository, FaturaItemService> {
}