package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TipoContato;
import com.stfn2.ggas.domain.dtos.TipoContatoDTO;
import com.stfn2.ggas.domain.dtos.filter.TipoContatoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TipoContatoBasicDTO;
import com.stfn2.ggas.repositories.TipoContatoRepository;

@Service
public class TipoContatoService extends BaseService<TipoContato, TipoContatoDTO, TipoContatoBasicDTO, TipoContatoFilterDTO, TipoContatoRepository> {

}