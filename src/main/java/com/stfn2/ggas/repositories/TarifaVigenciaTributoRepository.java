package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaTributoFilterDTO;

@Repository
public interface TarifaVigenciaTributoRepository extends BaseRepository<TarifaVigenciaTributo, TarifaVigenciaTributoFilterDTO> {

}
