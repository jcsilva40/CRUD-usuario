package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import com.stfn2.ggas.domain.dtos.ClienteDebitoAutomaticoDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteDebitoAutomaticoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteDebitoAutomaticoFilterDTO;
import com.stfn2.ggas.repositories.ClienteDebitoAutomaticoRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteDebitoAutomaticoService extends BaseService<ClienteDebitoAutomatico, ClienteDebitoAutomaticoDTO, ClienteDebitoAutomaticoBasicDTO, ClienteDebitoAutomaticoFilterDTO, ClienteDebitoAutomaticoRepository> {

   public ClienteDebitoAutomatico obterClienteDebitoAutomatico(Long clienteId, Long contratoPaiId) {
      ClienteDebitoAutomatico clienteDebitoAutomatico = new ClienteDebitoAutomatico();
      clienteDebitoAutomatico = repository.obterClienteDebitoAutomatico(clienteId, contratoPaiId);

      return clienteDebitoAutomatico;
   }
}