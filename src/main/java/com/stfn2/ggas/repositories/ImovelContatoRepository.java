package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ImovelContato;
import com.stfn2.ggas.domain.dtos.filter.ImovelContatoFilterDTO;

@Repository
public interface ImovelContatoRepository extends BaseRepository<ImovelContato, ImovelContatoFilterDTO> {

}