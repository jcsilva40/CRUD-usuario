package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorOperacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoFilterDTO;

@Repository
public interface MedidorOperacaoRepository extends BaseRepository<MedidorOperacao, MedidorOperacaoFilterDTO> {

}