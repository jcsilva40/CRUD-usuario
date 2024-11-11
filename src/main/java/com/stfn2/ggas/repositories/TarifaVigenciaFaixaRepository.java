package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFaixaFilterDTO;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaVigenciaFaixaRepository extends BaseRepository<TarifaVigenciaFaixa, TarifaVigenciaFaixaFilterDTO> {

}
