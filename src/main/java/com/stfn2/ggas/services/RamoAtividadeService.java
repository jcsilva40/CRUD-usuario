package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeFilterDTO;
import com.stfn2.ggas.domain.dtos.RamoAtividadeDTO;
import com.stfn2.ggas.domain.dtos.basic.RamoAtividadeBasicDTO;
import com.stfn2.ggas.repositories.RamoAtividadeRepository;
import org.springframework.stereotype.Service;

@Service
public class RamoAtividadeService extends BaseService<RamoAtividade, RamoAtividadeDTO, RamoAtividadeBasicDTO, RamoAtividadeFilterDTO, RamoAtividadeRepository> {



}
