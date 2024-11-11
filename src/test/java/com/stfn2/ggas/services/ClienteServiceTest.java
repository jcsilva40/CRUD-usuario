package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ClienteContatoDTO;
import com.stfn2.ggas.domain.dtos.ClienteDTO;
import com.stfn2.ggas.domain.enumTypes.EnumTipoCliente;
import com.stfn2.ggas.repositories.ClienteRepository;
import com.stfn2.ggas.services.componentes.faturamento.uteis.ToolsFaturamento;
import com.stfn2.ggas.tools.ToolCPFAndCNPJ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private NacionalidadeService nacionalidadeService;

    @Mock
    private PessoaSexoService pessoaSexoService;

    @Mock
    private TipoContatoService tipoContatoService;

    @Mock
    private CepService cepService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        Field repositoryField = null;
        try {
            repositoryField = BaseService.class.getDeclaredField("repository");
            repositoryField.setAccessible(true);
            repositoryField.set(clienteService, clienteRepository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void beforeSave() {
        Cliente cliente = new Cliente();
        ClienteContato contato1 = new ClienteContato();
        contato1.setId(1L);
        ClienteContato contato2 = new ClienteContato();
        contato2.setId(2L);
        ClienteEndereco endereco1 = new ClienteEndereco();
        endereco1.setDescricao("Teste");
        ClienteEndereco endereco2 = new ClienteEndereco();
        endereco2.setDescricao("Teste2");

        cliente.setContatos(Arrays.asList(contato1, contato2));
        cliente.setEnderecos(Arrays.asList(endereco1, endereco2));

        Cliente result = clienteService.beforeSave(cliente);

        // Verifica se após o método está correto
        assertEquals(cliente, contato1.getCliente());
        assertEquals(cliente, contato2.getCliente());
        assertEquals(cliente, endereco1.getCliente());
        assertEquals(cliente, endereco2.getCliente());
    }

   // @Test
    void dtoToEntity() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpfCnpj("12345678000195");
        clienteDTO.setTipoCliente(EnumTipoCliente.CNPJ.getId());

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(nacionalidadeService.getById(anyLong())).thenReturn(new Nacionalidade());
        when(pessoaSexoService.getById(anyLong())).thenReturn(new PessoaSexo());
        when(tipoContatoService.getById(anyLong())).thenReturn(new TipoContato());
        when(cepService.getById(anyLong())).thenReturn(new Cep());

        Cliente cliente = clienteService.dtoToEntity(clienteDTO);

        assertEquals("12345678000195", cliente.getCnpj());
        assertEquals("", cliente.getCpf());

        clienteDTO.setTipoCliente(EnumTipoCliente.CPF.getId());
        clienteDTO.setCpfCnpj("12345678901");

        cliente = clienteService.dtoToEntity(clienteDTO);

        assertEquals("12345678901", cliente.getCpf());
        assertEquals("", cliente.getCnpj());
    }

    @Test
    void entityToDTO() {
        Cliente cliente = new Cliente();
        cliente.setCnpj(null);
        cliente.setCpf("12345678901");

        ClienteDTO clienteDTO = clienteService.entityToDTO(cliente);

        assertEquals("123.456.789-01", clienteDTO.getCpfCnpj());

        // Verifica que entrou corretamente nas duas partes do ternário, cnpj/cpf é formatado
        cliente.setCpf(null);
        cliente.setCnpj("12345678000195");

        clienteDTO = clienteService.entityToDTO(cliente);
        assertEquals("12.345.678/0001-95", clienteDTO.getCpfCnpj());
    }

    @Test
    void validDto() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Nome");
        dto.setNomeFantasia("Nome Fantasia");
        dto.setCpfCnpj("12345678000195");
        dto.setTipoCliente(EnumTipoCliente.CNPJ.getId());
        ClienteContatoDTO contatodto = new ClienteContatoDTO();
        ClienteContatoDTO contatodto2 = new ClienteContatoDTO();
        contatodto.setPrincipal(true);
        contatodto.setEmail("abcd@gmail.com");
        contatodto.setFone(40028922);
        contatodto.setFone2(40028921);
        dto.setContatos(Arrays.asList(contatodto, contatodto2));

        // validarCNPJ é método estático
        try (MockedStatic<ToolCPFAndCNPJ> staticTool = mockStatic(ToolCPFAndCNPJ.class)) {
            staticTool.when(() -> ToolCPFAndCNPJ.validarCNPJ(dto.getCpfCnpj())).thenReturn(true);

            // Quando está tudo certo, ele não vai throw nada
            assertDoesNotThrow(() -> clienteService.validDto(dto));
        }
    }

    @Test
    void validDto_NotOk() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Nome");
        dto.setNomeFantasia("Nome Fantasia");
        dto.setCpfCnpj("12345678000195");
        dto.setTipoCliente(EnumTipoCliente.CNPJ.getId());
        ClienteContatoDTO contatodto = new ClienteContatoDTO();
        ClienteContatoDTO contatodto2 = new ClienteContatoDTO();
        contatodto.setPrincipal(true);
        contatodto.setEmail("abcd@gmail.com");
        contatodto.setFone(40028922);
        contatodto.setFone2(40028921);
        dto.setContatos(Arrays.asList(contatodto, contatodto2));

        // Verifica se apresentou erro quando algum dos atributos obrigatórios foi null
        dto.setNome(null);
        assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));

        dto.setNome("Nome");
        dto.setNomeFantasia(null);
        assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));

        dto.setNomeFantasia("Nome Fantasia");
        dto.setCpfCnpj(null);
        assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));

        dto.setCpfCnpj("12345678000195");
        dto.setTipoCliente(null);
        assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));

        dto.setTipoCliente(EnumTipoCliente.CNPJ.getId());
        dto.setCpfCnpj("invalidCNPJ");
        try (MockedStatic<ToolCPFAndCNPJ> staticTool = mockStatic(ToolCPFAndCNPJ.class)) {
            staticTool.when(() -> ToolCPFAndCNPJ.validarCNPJ(dto.getCpfCnpj())).thenReturn(false);
            assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));
        }

        dto.setCpfCnpj("12345678000195");
        dto.setContatos(null);
        assertThrows(RuntimeException.class, () -> clienteService.validDto(dto));
    }

    @Test
    void findAllCliente() {
        List<ComboDTO> comboDTOList = Arrays.asList(new ComboDTO(1L, "Cliente 1"), new ComboDTO(2L, "Cliente 2"));
        when(clienteRepository.findAllCliente()).thenReturn(comboDTOList);

        List<ComboDTO> result = clienteService.findAllCliente();

        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getDescricao());
        assertEquals("Cliente 2", result.get(1).getDescricao());
    }

    //@Test
    void obterDadosRelatorioCodigoBarrasEndereco() throws NegocioException {
        Cliente cliente = new Cliente();
        ClienteEndereco endereco1 = new ClienteEndereco();
        endereco1.setCorrespondencia(true);
        endereco1.setCep(new Cep());
        endereco1.getCep().setNumeroCep("12345678");
        cliente.setEnderecos(Collections.singletonList(endereco1));

        try (MockedStatic<ToolsFaturamento> staticTool = mockStatic(ToolsFaturamento.class)) {
            staticTool.when(() -> ToolsFaturamento.calculoCodigoBarrasEndereco("12345678")).thenReturn("123456789012");

            Map<String, Object> result = clienteService.obterDadosRelatorioCodigoBarrasEndereco(cliente);

            assertEquals("123456789012", result.get("codigoBarraEndereco"));
            assertEquals(cliente.getNome(), result.get("nomeDestinatario"));
        }
    }
}