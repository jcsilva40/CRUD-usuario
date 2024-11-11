package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoMensagemVenciment;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemVencimentFilterDTO;

@Repository
public interface FaturamentoMensagemVencimentRepository extends BaseRepository<FaturamentoMensagemVenciment, FaturamentoMensagemVencimentFilterDTO> {

}