package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Devolucao;
import com.stfn2.ggas.domain.dtos.filter.DevolucaoFilterDTO;

@Repository
public interface DevolucaoRepository extends BaseRepository<Devolucao, DevolucaoFilterDTO> {

}