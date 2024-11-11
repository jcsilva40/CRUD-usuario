package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RecursoPermissao;
import com.stfn2.ggas.domain.dtos.filter.RecursoPermissaoFilterDTO;

@Repository
public interface RecursoPermissaoRepository extends BaseRepository<RecursoPermissao, RecursoPermissaoFilterDTO> {

}