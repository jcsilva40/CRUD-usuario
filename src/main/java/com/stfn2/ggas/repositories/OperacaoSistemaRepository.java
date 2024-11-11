package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.OperacaoSistema;
import com.stfn2.ggas.domain.dtos.filter.OperacaoSistemaFilterDTO;

@Repository
public interface OperacaoSistemaRepository extends BaseRepository<OperacaoSistema, OperacaoSistemaFilterDTO> {

}