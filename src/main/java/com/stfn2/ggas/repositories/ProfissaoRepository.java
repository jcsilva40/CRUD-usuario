package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Profissao;
import com.stfn2.ggas.domain.dtos.filter.ProfissaoFilterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissaoRepository extends BaseRepository<Profissao, ProfissaoFilterDTO> {

}