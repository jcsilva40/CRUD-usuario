package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Permissao;
import com.stfn2.ggas.domain.dtos.filter.PermissaoFilterDTO;

@Repository
public interface PermissaoRepository extends BaseRepository<Permissao, PermissaoFilterDTO> {

}