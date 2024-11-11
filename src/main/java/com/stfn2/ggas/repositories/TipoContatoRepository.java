package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TipoContato;
import com.stfn2.ggas.domain.dtos.filter.TipoContatoFilterDTO;

@Repository
public interface TipoContatoRepository extends BaseRepository<TipoContato, TipoContatoFilterDTO> {

}