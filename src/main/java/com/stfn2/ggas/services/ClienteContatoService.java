package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.ClienteContato;
import com.stfn2.ggas.domain.dtos.ClienteContatoDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteContatoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteContatoFilterDTO;
import com.stfn2.ggas.repositories.ClienteContatoRepository;
import com.stfn2.ggas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteContatoService extends BaseService<ClienteContato, ClienteContatoDTO, ClienteContatoBasicDTO,
        ClienteContatoFilterDTO, ClienteContatoRepository> {

    @Autowired
    private ClienteRepository   clienteRepository;

    public List<ClienteContatoBasicDTO> buscarClienteContato(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).get();
        List<ClienteContato> contatos = new ArrayList<>();

        List<ClienteContatoBasicDTO> dtos = this.repository.findByCliente(cliente).stream()
                .map(ent -> MapperImpl.parseObject(ent, ClienteContatoBasicDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

}

