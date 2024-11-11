package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaMensagem;
import com.stfn2.ggas.domain.dtos.filter.FaturaMensagemFilterDTO;

@Repository
public interface FaturaMensagemRepository extends BaseRepository<FaturaMensagem, FaturaMensagemFilterDTO> {

}