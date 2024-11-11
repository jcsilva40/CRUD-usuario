package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaMotivoRevisao;
import com.stfn2.ggas.domain.dtos.filter.FaturaMotivoRevisaoFilterDTO;

@Repository
public interface FaturaMotivoRevisaoRepository extends BaseRepository<FaturaMotivoRevisao, FaturaMotivoRevisaoFilterDTO> {

}