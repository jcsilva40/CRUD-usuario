package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CorretorVazao;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoFilterDTO;

@Repository
public interface CorretorVazaoRepository extends BaseRepository<CorretorVazao, CorretorVazaoFilterDTO> {

}