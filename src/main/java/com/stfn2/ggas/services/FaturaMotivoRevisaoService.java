package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaMotivoRevisao;
import com.stfn2.ggas.domain.dtos.FaturaMotivoRevisaoDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaMotivoRevisaoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaMotivoRevisaoBasicDTO;
import com.stfn2.ggas.repositories.FaturaMotivoRevisaoRepository;

@Service
public class FaturaMotivoRevisaoService extends BaseService<FaturaMotivoRevisao, FaturaMotivoRevisaoDTO, FaturaMotivoRevisaoBasicDTO, FaturaMotivoRevisaoFilterDTO, FaturaMotivoRevisaoRepository> {

}