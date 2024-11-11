package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContratoCliente;
import com.stfn2.ggas.domain.dtos.filter.ContratoClienteFilterDTO;

@Repository
public interface ContratoClienteRepository extends BaseRepository<ContratoCliente, ContratoClienteFilterDTO> {

}