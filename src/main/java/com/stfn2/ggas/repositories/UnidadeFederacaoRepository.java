package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.UnidadeFederacao;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFederacaoFilterDTO;

import java.util.Optional;

@Repository
public interface UnidadeFederacaoRepository extends BaseRepository<UnidadeFederacao, UnidadeFederacaoFilterDTO> {

    UnidadeFederacao findBySigla(String sigla);
}
