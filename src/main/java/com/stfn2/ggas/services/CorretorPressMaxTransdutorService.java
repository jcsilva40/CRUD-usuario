package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.CorretorPressMaxTransdutor;
import com.stfn2.ggas.domain.dtos.CorretorPressMaxTransdutorDTO;
import com.stfn2.ggas.domain.dtos.filter.CorretorPressMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CorretorPressMaxTransdutorBasicDTO;
import com.stfn2.ggas.repositories.CorretorPressMaxTransdutorRepository;

@Service
public class CorretorPressMaxTransdutorService extends BaseService<CorretorPressMaxTransdutor, CorretorPressMaxTransdutorDTO, CorretorPressMaxTransdutorBasicDTO, CorretorPressMaxTransdutorFilterDTO, CorretorPressMaxTransdutorRepository> {

}