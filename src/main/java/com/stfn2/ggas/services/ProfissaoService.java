package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Profissao;
import com.stfn2.ggas.domain.dtos.ProfissaoDTO;
import com.stfn2.ggas.domain.dtos.basic.ProfissaoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ProfissaoFilterDTO;
import com.stfn2.ggas.repositories.ProfissaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfissaoService extends BaseService<Profissao, ProfissaoDTO, ProfissaoBasicDTO, ProfissaoFilterDTO,
        ProfissaoRepository> {
}