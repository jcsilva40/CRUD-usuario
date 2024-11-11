package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FinanciamentoTipo;
import com.stfn2.ggas.domain.dtos.filter.FinanciamentoTipoFilterDTO;

@Repository
public interface FinanciamentoTipoRepository extends BaseRepository<FinanciamentoTipo, FinanciamentoTipoFilterDTO> {

}
