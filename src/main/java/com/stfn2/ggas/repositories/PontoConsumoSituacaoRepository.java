package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.PontoConsumoSituacao;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoSituacaoFilterDTO;

@Repository
public interface PontoConsumoSituacaoRepository extends BaseRepository<PontoConsumoSituacao, PontoConsumoSituacaoFilterDTO> {

}
