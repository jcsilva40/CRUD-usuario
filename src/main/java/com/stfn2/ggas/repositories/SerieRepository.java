package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Serie;
import com.stfn2.ggas.domain.dtos.filter.SerieFilterDTO;

@Repository
public interface SerieRepository extends BaseRepository<Serie, SerieFilterDTO> {

}