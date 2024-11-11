package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaGeral;
import com.stfn2.ggas.domain.dtos.FaturaGeralDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaGeralFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaGeralBasicDTO;
import com.stfn2.ggas.repositories.FaturaGeralRepository;

@Service
public class FaturaGeralService extends BaseService<FaturaGeral, FaturaGeralDTO, FaturaGeralBasicDTO, FaturaGeralFilterDTO, FaturaGeralRepository> {

}