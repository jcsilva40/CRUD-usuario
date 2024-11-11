package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Rubrica;
import com.stfn2.ggas.domain.RubricaTributo;
import com.stfn2.ggas.domain.dtos.filter.RubricaTributoFilterDTO;
import com.stfn2.ggas.domain.dtos.RubricaTributoDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaTributoBasicDTO;
import com.stfn2.ggas.repositories.RubricaTributoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricaTributoService extends BaseService<RubricaTributo, RubricaTributoDTO, RubricaTributoBasicDTO, RubricaTributoFilterDTO, RubricaTributoRepository> {

   public List<RubricaTributo> obterRubricaTributos(Rubrica rubrica) {
      return repository.findByRubrica(rubrica);
   }
}

