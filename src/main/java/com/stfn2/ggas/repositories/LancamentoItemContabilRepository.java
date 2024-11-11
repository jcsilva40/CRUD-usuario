package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import com.stfn2.ggas.domain.dtos.filter.LancamentoItemContabilFilterDTO;

@Repository
public interface LancamentoItemContabilRepository extends BaseRepository<LancamentoItemContabil, LancamentoItemContabilFilterDTO> {

}