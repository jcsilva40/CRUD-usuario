package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoHistoricoFilterDTO;

@Repository
public interface MedidorOperacaoHistoricoRepository extends BaseRepository<MedidorOperacaoHistorico, MedidorOperacaoHistoricoFilterDTO> {

}