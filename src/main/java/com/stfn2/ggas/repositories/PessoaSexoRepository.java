package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.PessoaSexo;
import com.stfn2.ggas.domain.dtos.filter.PessoaSexoFilterDTO;

@Repository
public interface PessoaSexoRepository extends BaseRepository<PessoaSexo, PessoaSexoFilterDTO> {

}