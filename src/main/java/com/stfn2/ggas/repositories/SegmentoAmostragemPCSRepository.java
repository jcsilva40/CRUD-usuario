package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.SegmentoAmostragemPCSFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.SegmentoAmostragemPCS;

@Repository
public interface SegmentoAmostragemPCSRepository extends BaseRepository<SegmentoAmostragemPCS, SegmentoAmostragemPCSFilterDTO> {

}
