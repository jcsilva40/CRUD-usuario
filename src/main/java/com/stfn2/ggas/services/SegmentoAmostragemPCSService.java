package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.dtos.filter.SegmentoAmostragemPCSFilterDTO;
import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.SegmentoAmostragemPCS;
import com.stfn2.ggas.domain.dtos.SegmentoAmostragemPCSDTO;
import com.stfn2.ggas.domain.dtos.basic.SegmentoAmostragemPCSBasicDTO;
import com.stfn2.ggas.repositories.SegmentoAmostragemPCSRepository;

@Service
public class SegmentoAmostragemPCSService extends BaseService<SegmentoAmostragemPCS,
        SegmentoAmostragemPCSDTO, SegmentoAmostragemPCSBasicDTO, SegmentoAmostragemPCSFilterDTO,
        SegmentoAmostragemPCSRepository> {

}

