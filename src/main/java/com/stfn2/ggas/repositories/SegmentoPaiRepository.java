package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.SegmentoPai;
import com.stfn2.ggas.domain.dtos.filter.SegmentoPaiFilterDTO;

@Repository
public interface SegmentoPaiRepository extends BaseRepository<SegmentoPai, SegmentoPaiFilterDTO> {

}