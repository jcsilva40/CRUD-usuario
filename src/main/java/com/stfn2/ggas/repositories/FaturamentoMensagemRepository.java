package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemFilterDTO;

@Repository
public interface FaturamentoMensagemRepository extends BaseRepository<FaturamentoMensagem, FaturamentoMensagemFilterDTO> {

}