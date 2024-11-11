package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.LiberacaoCronogramaFaturamento;
import com.stfn2.ggas.domain.dtos.filter.LiberacaoCronogramaFaturamentoFilterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface LiberacaoCronogramaFaturamentoRepository extends BaseRepository<LiberacaoCronogramaFaturamento, LiberacaoCronogramaFaturamentoFilterDTO> {

}
