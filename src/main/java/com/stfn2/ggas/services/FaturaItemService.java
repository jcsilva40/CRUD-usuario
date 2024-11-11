package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.dtos.FaturaItemDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaItemBasicDTO;
import com.stfn2.ggas.repositories.FaturaItemRepository;

@Service
public class FaturaItemService extends BaseService<FaturaItem, FaturaItemDTO, FaturaItemBasicDTO, FaturaItemFilterDTO, FaturaItemRepository> {

}