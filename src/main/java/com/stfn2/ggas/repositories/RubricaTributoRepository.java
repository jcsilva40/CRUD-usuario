package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.Rubrica;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RubricaTributo;
import com.stfn2.ggas.domain.dtos.filter.RubricaTributoFilterDTO;

import java.util.List;

@Repository
public interface RubricaTributoRepository extends BaseRepository<RubricaTributo, RubricaTributoFilterDTO> {

   List<RubricaTributo> findByRubrica(Rubrica rubrica);
   void deleteByRubrica(Rubrica rubrica);

}
