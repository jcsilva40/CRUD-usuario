package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaTributacao;
import com.stfn2.ggas.domain.dtos.filter.FaturaTributacaoFilterDTO;

@Repository
public interface FaturaTributacaoRepository extends BaseRepository<FaturaTributacao, FaturaTributacaoFilterDTO> {

}