package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ImovelContato;
import com.stfn2.ggas.domain.dtos.ImovelContatoDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelContatoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelContatoBasicDTO;
import com.stfn2.ggas.repositories.ImovelContatoRepository;

@Service
public class ImovelContatoService extends BaseService<ImovelContato, ImovelContatoDTO, ImovelContatoBasicDTO, ImovelContatoFilterDTO, ImovelContatoRepository> {

}