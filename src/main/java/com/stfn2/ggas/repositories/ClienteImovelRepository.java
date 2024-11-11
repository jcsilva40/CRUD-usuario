package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ClienteImovel;
import com.stfn2.ggas.domain.dtos.filter.ClienteImovelFilterDTO;

@Repository
public interface ClienteImovelRepository extends BaseRepository<ClienteImovel, ClienteImovelFilterDTO> {

}