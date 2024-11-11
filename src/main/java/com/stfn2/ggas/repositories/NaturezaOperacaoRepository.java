package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.NaturezaOperacao;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoFilterDTO;

@Repository
public interface NaturezaOperacaoRepository extends BaseRepository<NaturezaOperacao, NaturezaOperacaoFilterDTO> {

}