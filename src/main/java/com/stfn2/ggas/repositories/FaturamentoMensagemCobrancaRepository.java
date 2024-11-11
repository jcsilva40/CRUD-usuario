package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoMensagemCobranca;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemCobrancaFilterDTO;

@Repository
public interface FaturamentoMensagemCobrancaRepository extends BaseRepository<FaturamentoMensagemCobranca, FaturamentoMensagemCobrancaFilterDTO> {

}