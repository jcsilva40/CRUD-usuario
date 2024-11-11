package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContratoPontoConsumoItemFaturamento;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoItemFaturamentoFilterDTO;

@Repository
public interface ContratoPontoConsumoItemFaturamentoRepository extends BaseRepository<ContratoPontoConsumoItemFaturamento, ContratoPontoConsumoItemFaturamentoFilterDTO> {

}