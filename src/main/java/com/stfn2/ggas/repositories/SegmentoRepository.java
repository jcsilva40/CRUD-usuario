package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.dtos.filter.SegmentoFilterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends BaseRepository<Segmento, SegmentoFilterDTO> {

}
