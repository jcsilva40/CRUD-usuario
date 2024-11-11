package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.NomenclaturaComumMercosul;
import com.stfn2.ggas.domain.dtos.filter.NomenclaturaComumMercosulFilterDTO;

@Repository
public interface NomenclaturaComumMercosulRepository extends BaseRepository<NomenclaturaComumMercosul, NomenclaturaComumMercosulFilterDTO> {

}
