package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.BIStefanini;
import com.stfn2.ggas.domain.dtos.filter.BIStefaniniFilterDTO;

@Repository
public interface BIStefaniniRepository extends BaseRepository<BIStefanini, BIStefaniniFilterDTO> {

}