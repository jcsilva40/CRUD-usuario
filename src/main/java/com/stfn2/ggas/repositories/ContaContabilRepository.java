package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.ContaContabilFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContaContabil;

@Repository
public interface ContaContabilRepository extends BaseRepository<ContaContabil, ContaContabilFilterDTO> {

}
