package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorMotivoOperacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorMotivoOperacaoFilterDTO;

@Repository
public interface MedidorMotivoOperacaoRepository extends BaseRepository<MedidorMotivoOperacao, MedidorMotivoOperacaoFilterDTO> {

}