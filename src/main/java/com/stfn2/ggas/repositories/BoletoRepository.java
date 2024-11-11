package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Boleto;
import com.stfn2.ggas.domain.dtos.filter.BoletoFilterDTO;

@Repository
public interface BoletoRepository extends BaseRepository<Boleto, BoletoFilterDTO> {

}