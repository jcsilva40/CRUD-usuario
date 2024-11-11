package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorMovimentacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorMovimentacaoFilterDTO;

@Repository
public interface MedidorMovimentacaoRepository extends BaseRepository<MedidorMovimentacao, MedidorMovimentacaoFilterDTO> {

}