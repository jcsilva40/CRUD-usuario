package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.Rubrica;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;
import com.stfn2.ggas.domain.dtos.filter.RubricaValorRegulamentadoFilterDTO;

import java.util.Optional;

@Repository
public interface RubricaValorRegulamentadoRepository extends BaseRepository<RubricaValorRegulamentado, RubricaValorRegulamentadoFilterDTO> {

    Optional<RubricaValorRegulamentado> findByRubricaAndHabilitado(Rubrica rubrica, Boolean habilitado);

    void deleteByRubrica(Rubrica rubrica);

}
