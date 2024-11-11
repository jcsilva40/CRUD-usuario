package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaGeral;
import com.stfn2.ggas.domain.dtos.filter.FaturaGeralFilterDTO;

@Repository
public interface FaturaGeralRepository extends BaseRepository<FaturaGeral, FaturaGeralFilterDTO> {

}