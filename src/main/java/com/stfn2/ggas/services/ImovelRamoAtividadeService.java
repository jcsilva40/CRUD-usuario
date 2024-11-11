package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ImovelRamoAtividade;
import com.stfn2.ggas.domain.dtos.ImovelRamoAtividadeDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelRamoAtividadeFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelRamoAtividadeBasicDTO;
import com.stfn2.ggas.repositories.ImovelRamoAtividadeRepository;

@Service
public class ImovelRamoAtividadeService extends BaseService<ImovelRamoAtividade, ImovelRamoAtividadeDTO, ImovelRamoAtividadeBasicDTO, ImovelRamoAtividadeFilterDTO, ImovelRamoAtividadeRepository> {

}