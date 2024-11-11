package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.ClienteContato;
import com.stfn2.ggas.domain.dtos.filter.ClienteContatoFilterDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteContatoRepository extends BaseRepository<ClienteContato, ClienteContatoFilterDTO> {

   List<ClienteContato> findByCliente(Cliente cliente);
}
