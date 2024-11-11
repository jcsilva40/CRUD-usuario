package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.ConvertObj;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.ClienteContato;
import com.stfn2.ggas.domain.ClienteEndereco;
import com.stfn2.ggas.domain.dtos.ClienteContatoDTO;
import com.stfn2.ggas.domain.dtos.ClienteDTO;
import com.stfn2.ggas.domain.dtos.ClienteEnderecoDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteFilterDTO;
import com.stfn2.ggas.domain.enumTypes.EnumTipoCliente;
import com.stfn2.ggas.repositories.ClienteRepository;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ToolsFaturamento;
import com.stfn2.ggas.tools.ToolCPFAndCNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService extends BaseService<Cliente, ClienteDTO, ClienteBasicDTO, ClienteFilterDTO, ClienteRepository> {

    @Autowired
    private NacionalidadeService nacionalidadeService;

    @Autowired
    private PessoaSexoService pessoaSexoService;

    @Autowired
    private TipoContatoService tipoContatoService;

    @Autowired
    private CepService cepService;

    @Override
    public Cliente beforeSave(Cliente entity) {
        entity.getContatos().forEach(item -> item.setCliente(entity));
        entity.getEnderecos().forEach(item -> item.setCliente(entity));
        if(entity.getCpf() != null) entity.setCpf(entity.getCpf().replace("-", "").replace(".", ""));
        if(entity.getCnpj() != null) entity.setCnpj(entity.getCnpj().replace("-", "").replace(".", "").replace("/",
                ""));
        return entity;
    }

    @Override
    public Cliente dtoToEntity(ClienteDTO dto) {
        Cliente cliente = getById(dto.getId());
        if (cliente == null) {
            cliente = new Cliente();
        }

        cliente.setNome(dto.getNome());
        if(dto.getClienteSituacao() != 1L){
            cliente.setClienteSituacao(1L);
        }
        cliente.setNomeFantasia(dto.getNomeFantasia());
        cliente.setNomeAbreviado(dto.getNomeAbreviado());
        cliente.setTipoCliente(dto.getTipoCliente());
        if (dto.getNacionalidadeId() != null)
            cliente.setNacionalidade(nacionalidadeService.getById(dto.getNacionalidadeId()));
        if (dto.getSexoId() != null)
            cliente.setSexo(pessoaSexoService.getById(dto.getSexoId()));
        cliente.setRg(dto.getRg());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setIeFaturamento(dto.getIeFaturamento());
        cliente.setInscricaoEstadual(dto.getInscricaoEstadual());
        cliente.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        cliente.setRegimeRecolhimento(dto.getRegimeRecolhimento());

        if (EnumTipoCliente.CNPJ.getId().equals(dto.getTipoCliente())) {
            cliente.setCnpj(dto.getCpfCnpj());
            cliente.setCpf("");
        } else {
            cliente.setCpf(dto.getCpfCnpj());
            cliente.setCnpj("");
        }

        // Convertendo os contatos
        ConvertObj<ClienteContato, ClienteContatoDTO> convertObjClienteContato = new ConvertObj<ClienteContato, ClienteContatoDTO>() {
            @Override
            public void update(ClienteContato d, ClienteContatoDTO o) {
                d.setNome(o.getNome());
                d.setEmail(o.getEmail());
                d.setCpf(o.getCpf());
                d.setRg(o.getRg());
                d.setObservacao(o.getObservacao());
                d.setRamal(o.getRamal());
                d.setRamal2(o.getRamal2());
                d.setCodigoDDD(o.getCodigoDDD());
                d.setCodigoDDD2(o.getCodigoDDD2());
                d.setFone(o.getFone());
                d.setFone2(o.getFone2());
                d.setPrincipal(o.getPrincipal());
                d.setTipoContato(tipoContatoService.getById(o.getTipoContatoId()));
            }
        };

        // Lista de contatos atualizada no DTO
        List<ClienteContatoDTO> contatosDTO = dto.getContatos();

        // Identifica e remove os contatos que não estão mais no DTO
        cliente.getContatos().removeIf(existingContato ->
                contatosDTO.stream().noneMatch(dtoContato ->
                        dtoContato.getId() != null && dtoContato.getId().equals(existingContato.getId())
                )
        );

        // Atualiza ou adiciona novos contatos
        for (ClienteContatoDTO ccDto : contatosDTO) {
            boolean exists = cliente.getContatos().stream()
                    .anyMatch(n -> n.getId() != null && ccDto.getId() != null && ccDto.getId().equals(n.getId()));
            if (!exists) {
                ClienteContato newContato = new ClienteContato();
                newContato.setCliente(cliente);
                convertObjClienteContato.update(newContato, ccDto);
                cliente.getContatos().add(newContato);
            } else {
                cliente.getContatos().stream()
                        .filter(n -> ccDto.getId() != null && ccDto.getId().equals(n.getId()))
                        .findFirst()
                        .ifPresent(existingContato -> convertObjClienteContato.update(existingContato, ccDto));
            }
        }

    ConvertObj<ClienteEndereco, ClienteEnderecoDTO> convertObjClienteEndereco = new ConvertObj<ClienteEndereco, ClienteEnderecoDTO>() {
            @Override
            public void update(ClienteEndereco d, ClienteEnderecoDTO o) {
                d.setId(o.getId());
                d.setComplemento(o.getComplemento());
                d.setNumero(o.getNumero());
                d.setCep(cepService.getById(o.getCepId()));
                d.setIndicadorPrincipal(o.getIndicadorPrincipal());
            }
        };

        List<ClienteEnderecoDTO> enderecosDTO = dto.getEnderecos();

        cliente.getEnderecos().removeIf(existingEndereco ->
                enderecosDTO.stream().noneMatch(dtoEndereco ->
                        dtoEndereco.getId() != null && dtoEndereco.getId().equals(existingEndereco.getId())
                )
        );

        for (ClienteEnderecoDTO ceDto : enderecosDTO) {
            boolean exists = cliente.getEnderecos().stream()
                    .anyMatch(n -> n.getId() != null && ceDto.getId() != null && ceDto.getId().equals(n.getId()));
            if (!exists) {
                ClienteEndereco newEndereco = new ClienteEndereco();
                newEndereco.setCliente(cliente);
                convertObjClienteEndereco.update(newEndereco, ceDto);
                cliente.getEnderecos().add(newEndereco);
            } else {
                cliente.getEnderecos().stream()
                        .filter(n -> ceDto.getId() != null && ceDto.getId().equals(n.getId()))
                        .findFirst()
                        .ifPresent(existingEndereco -> convertObjClienteEndereco.update(existingEndereco, ceDto));
            }
        }

        return cliente;
    }


    @Override
    public ClienteDTO entityToDTO(Cliente entity) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setNomeAbreviado(entity.getNomeAbreviado());
        dto.setRegimeRecolhimento(entity.getRegimeRecolhimento());
        dto.setInscricaoEstadual(entity.getInscricaoEstadual());
        dto.setInscricaoMunicipal(entity.getInscricaoMunicipal());
        dto.setClienteSituacao(entity.getClienteSituacao());
        if(entity.getSexo() != null) dto.setSexoId(entity.getSexo().getId());
        dto.setRg(entity.getRg());
        dto.setTipoCliente(entity.getTipoCliente());
        if(entity.getNacionalidade() != null) dto.setNacionalidadeId(entity.getNacionalidade().getId());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setIeFaturamento(entity.getIeFaturamento());
        String cpfCnpj = entity.getCnpj() != null ? entity.getCnpj() : entity.getCpf();
        dto.setCpfCnpj(formatCpfCnpj(cpfCnpj));

        for(ClienteContato i : entity.getContatos()) {
            ClienteContatoDTO clDto = new ClienteContatoDTO();
            clDto.setId(i.getId());
            clDto.setNome(i.getNome());
            clDto.setCpf(i.getCpf());
            clDto.setRg(i.getRg());
            clDto.setEmail(i.getEmail());
            clDto.setObservacao(i.getObservacao());
            clDto.setRamal(i.getRamal());
            clDto.setRamal2(i.getRamal2());
            clDto.setCodigoDDD(i.getCodigoDDD());
            clDto.setCodigoDDD2(i.getCodigoDDD2());
            clDto.setFone(i.getFone());
            clDto.setFone2(i.getFone2());
            clDto.setPrincipal(i.getPrincipal());
            clDto.setTipoContatoId(i.getTipoContato().getId());

            dto.getContatos().add(clDto);
        }

        for(ClienteEndereco e : entity.getEnderecos()){
            ClienteEnderecoDTO ceDto = new ClienteEnderecoDTO();
            ceDto.setId(e.getId());
            ceDto.setComplemento(e.getComplemento());
            ceDto.setNumero(e.getNumero());
            ceDto.setCepId(e.getCep().getId());
            ceDto.setCepBairro(e.getCep().getBairro());
            ceDto.setCepLogradouro(e.getCep().getLogradouro());
            ceDto.setCepNumeroCep(e.getCep().getNumeroCep());
            ceDto.setCepMunicipioDescricao(e.getCep().getMunicipio().getDescricao());
            ceDto.setCepMunicipioUfDescricao(e.getCep().getMunicipio().getUf().getDescricao());
            ceDto.setIndicadorPrincipal(e.getIndicadorPrincipal());

        }

        return dto;
    }

    private String formatCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null) {
            return null;
        }

        if (cpfCnpj.length() == 11) { // CPF
            return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } else if (cpfCnpj.length() == 14) { // CNPJ
            return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        } else {
            return cpfCnpj;
        }
    }


    @Override
    public void validDto(ClienteDTO dto) {
        if (!hasContent(dto.getNome())) addErro("nome", "Campo Nome é obrigatório!");

        if (!isPositive(dto.getTipoCliente())) addErro("tipoCliente", "Campo Tipo Cliente é obrigatório");
        else {
            if (EnumTipoCliente.CNPJ.getId().equals(dto.getTipoCliente())) {
                if (!hasContent(dto.getCpfCnpj())) addErro("cpfCnpj", "Campo CNPJ é obrigatório!");
                else if (!ToolCPFAndCNPJ.validarCNPJ(dto.getCpfCnpj())) addErro("cpfCnpj", "CNPJ inválido");
                if (!hasContent(dto.getNomeFantasia())) addErro("nomeFantasia", "Campo Nome Fantasia é obrigatório");
            } else {
                if (!hasContent(dto.getCpfCnpj())) addErro("cpfCnpj", "Campo CFP é obrigatório!");
                else if (!ToolCPFAndCNPJ.validarCPF(dto.getCpfCnpj())) addErro("cpfCnpj", "CPF inválido");
            }
        }
        if (isNull(dto.getContatos()) || dto.getContatos().isEmpty()) {
            addErro("contatos", "Pelo menos um contato deve ser informado!");
        } else {
            if (dto.getContatos().size() > 1) {
                if (dto.getContatos().stream().noneMatch(ClienteContatoDTO::getPrincipal)) {
                    addErro("email", "Um e-mail deve ser marcado como principal!");
                }
            } else {
                if (!hasContent(dto.getContatos().getFirst().getEmail())) {
                    addErro("email", "E-mail de contato deve ser informado para ter marcação como principal!");
                }
            }
        }
    }

    public List<ComboDTO> findAllCliente() {
        return this.repository.findAllCliente();
    }

    public Map<String, Object> obterDadosRelatorioCodigoBarrasEndereco(Cliente cliente) throws NegocioException {

        Map<String, Object> filtro = new HashMap<>();

        Collection<ClienteEndereco> enderecos = cliente.getEnderecos();

        for (ClienteEndereco clienteEndereco : enderecos) {
            if (clienteEndereco.getCorrespondencia()) {

                String codigoBarra = ToolsFaturamento.calculoCodigoBarrasEndereco(clienteEndereco.getCep().getNumeroCep());

                filtro.put("nomeDestinatario", cliente.getNome());
                filtro.put("ruaNumeroComplemento", clienteEndereco.getEnderecoFormatadoRuaNumeroComplemento());
                filtro.put("bairro", clienteEndereco.getCep().getBairro());
                filtro.put("municipioUF", clienteEndereco.getEnderecoFormatadoMunicipioUF());
                filtro.put("cep", clienteEndereco.getCep());
                filtro.put("codigoBarraEndereco", codigoBarra);

                break;
            }

        }

        return filtro;
    }
}

