package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.enumTypes.ClienteImovelRelacionamentoEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelSituacaoEnum;
import com.stfn2.ggas.services.core.GenericTests;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class ImovelServiceTest implements GenericTests {

    @InjectMocks
    private ImovelService planetService;

    //@Override
   // @Test
    public void entityToDTOTest() {
        Imovel imovel = new Imovel();
        imovel.setId(1L);
        imovel.setDescricao("Imovel 1");

        PontoConsumo pc1 = new PontoConsumo();
        pc1.setDescricao("Ponto Consumo 1");
        pc1.setId(1L);

        RamoAtividade r1 = new RamoAtividade();
        r1.setDescricao("Referencia 1");
        r1.setId(1L);

        Rota rt1 = new Rota();
        rt1.setDescricao("Rota 1");
        rt1.setId(1L);
        pc1.setRota(rt1);

        PontoConsumo pc2 = new PontoConsumo();
        pc2.setDescricao("Ponto Consumo 2");
        pc2.setId(2L);


        RamoAtividade r2 = new RamoAtividade();
        r2.setDescricao("Referencia 2");
        r2.setId(2L);

        imovel.getPontoConsumos().add(pc1);
        imovel.getPontoConsumos().add(pc2);

        Rota rt2 = new Rota();
        rt2.setDescricao("Rota 2");
        rt2.setId(2L);
        pc2.setRota(rt2);

        ClienteImovel clienteImovel = new ClienteImovel();
        clienteImovel.setId(1L);
        clienteImovel.setDescricao("Cliente 1");
        clienteImovel.setRelacionamento(ClienteImovelRelacionamentoEnum.ADMINISTRADOR);
        clienteImovel.setInicioRelacionamento(LocalDate.of(2024, 6, 20));

        Cliente cliente = new Cliente();
        cliente.setId(0L);
        cliente.setNome("Compagas");
        clienteImovel.setCliente(cliente);

    //    imovel.setClienteImovel(clienteImovel);

        ImovelDTO dto = planetService.entityToDTO(imovel);

        assertEquals(imovel.getId(), dto.getId());

        assertEquals(imovel.getDescricao(), dto.getDescricao());
        assertFalse(dto.getPontoConsumos().isEmpty());
        /*
        assertEquals(imovel.getClienteImovel().getRelacionamento(), dto.getClienteImovelRelacionamento());
        assertEquals(imovel.getClienteImovel().getId(), dto.getClienteImovelId());
        assertEquals(imovel.getClienteImovel().getCliente().getId(), dto.getClienteImovelClienteId());

        assertEquals(imovel.getClienteImovel().getInicioRelacionamento(), dto.getClienteImovelInicioRelacionamento());
*/
        PontoConsumoDTO pcDto1 = dto.getPontoConsumos().getFirst();
        assertEquals(pc1.getId(), pcDto1.getId());
        assertEquals(pc1.getDescricao(), pcDto1.getDescricao());

    }

    @Override
   // @Test
    public void dtoToEntityTest() {
        ImovelDTO dto = new ImovelDTO();
      //  dto.setClienteImovelId(1L);
       // dto.setClienteImovelInicioRelacionamento(LocalDate.of(2024, 6, 20));
        dto.setImovelSituacao(ImovelSituacaoEnum.EM_PROSPECCAO);
       // dto.setClienteImovelRelacionamento(ClienteImovelRelacionamentoEnum.ADMINISTRADOR);

        Imovel imovel = planetService.dtoToEntity(dto);

        assertEquals(dto.getImovelSituacao(), imovel.getImovelSituacao());
    //    assertEquals(dto.getClienteImovelInicioRelacionamento(), imovel.getClienteImovel().getInicioRelacionamento());
     //   assertEquals(dto.getClienteImovelRelacionamento(), imovel.getClienteImovel().getRelacionamento());
    }
}