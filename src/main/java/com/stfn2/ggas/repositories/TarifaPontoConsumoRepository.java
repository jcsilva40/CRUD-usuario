package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TarifaPontoConsumo;
import com.stfn2.ggas.domain.dtos.filter.TarifaPontoConsumoFilterDTO;

@Repository
public interface TarifaPontoConsumoRepository extends BaseRepository<TarifaPontoConsumo, TarifaPontoConsumoFilterDTO> {

}
