package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CorretorVazaoMovimentacao;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoMovimentacaoFilterDTO;

@Repository
public interface CorretorVazaoMovimentacaoRepository extends BaseRepository<CorretorVazaoMovimentacao, CorretorVazaoMovimentacaoFilterDTO> {

}