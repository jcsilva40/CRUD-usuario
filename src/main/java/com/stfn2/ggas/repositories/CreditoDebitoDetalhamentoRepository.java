package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CreditoDebitoDetalhamento;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoDetalhamentoFilterDTO;

import java.util.List;

@Repository
public interface CreditoDebitoDetalhamentoRepository extends BaseRepository<CreditoDebitoDetalhamento,
        CreditoDebitoDetalhamentoFilterDTO> {

    List<CreditoDebitoDetalhamento> findByCreditoDebitoARealizarId(Long creditoDebitoARealizarId);

}