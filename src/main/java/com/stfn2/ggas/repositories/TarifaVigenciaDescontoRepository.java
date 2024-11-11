package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaDescontoFilterDTO;

@Repository
public interface TarifaVigenciaDescontoRepository extends BaseRepository<TarifaVigenciaDesconto, TarifaVigenciaDescontoFilterDTO> {

}
