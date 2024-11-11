package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import com.stfn2.ggas.domain.dtos.LancamentoItemContabilDTO;
import com.stfn2.ggas.domain.dtos.filter.LancamentoItemContabilFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.LancamentoItemContabilBasicDTO;
import com.stfn2.ggas.repositories.LancamentoItemContabilRepository;

@Service
public class LancamentoItemContabilService extends BaseService<LancamentoItemContabil, LancamentoItemContabilDTO, LancamentoItemContabilBasicDTO, LancamentoItemContabilFilterDTO, LancamentoItemContabilRepository> {

}