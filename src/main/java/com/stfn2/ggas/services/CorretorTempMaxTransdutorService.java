package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CorretorTempMaxTransdutor;
import com.stfn2.ggas.domain.dtos.CorretorTempMaxTransdutorDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorTempMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorTempMaxTransdutorBasicDTO;
import com.stfn2.ggas.repositories.CorretorTempMaxTransdutorRepository;

@Service
public class CorretorTempMaxTransdutorService extends BaseService<CorretorTempMaxTransdutor, CorretorTempMaxTransdutorDTO, CorretorTempMaxTransdutorBasicDTO, CorretorTempMaxTransdutorFilterDTO, CorretorTempMaxTransdutorRepository> {

}