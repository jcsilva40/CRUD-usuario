package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.GrupoPermissao;
import com.stfn2.ggas.domain.dtos.filter.GrupoPermissaoFilterDTO;

@Repository
public interface GrupoPermissaoRepository extends BaseRepository<GrupoPermissao, GrupoPermissaoFilterDTO> {

}