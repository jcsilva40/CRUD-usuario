package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.NaturezaOperacaoCfop;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoCfopFilterDTO;

@Repository
public interface NaturezaOperacaoCfopRepository extends BaseRepository<NaturezaOperacaoCfop, NaturezaOperacaoCfopFilterDTO> {

}