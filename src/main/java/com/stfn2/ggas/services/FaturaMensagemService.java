package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturaMensagem;
import com.stfn2.ggas.domain.dtos.FaturaMensagemDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturaMensagemBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturaMensagemFilterDTO;
import com.stfn2.ggas.repositories.FaturaMensagemRepository;

@Service
public class FaturaMensagemService extends BaseService<FaturaMensagem, FaturaMensagemDTO, FaturaMensagemBasicDTO, FaturaMensagemFilterDTO, FaturaMensagemRepository> {

}