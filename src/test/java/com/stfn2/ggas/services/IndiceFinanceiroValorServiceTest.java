package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import com.stfn2.ggas.repositories.IndiceFinanceiroValorNominalRepository;
import com.stfn2.ggas.services.core.GenericTests;
import com.stfn2.ggas.tools.ToolDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndiceFinanceiroValorServiceTest implements GenericTests {
    @InjectMocks
    private IndiceFinanceiroValorService service;
    @Mock
    private IndiceFinanceiroService indiceFinanceiroService;

    @Mock
    private IndiceFinanceiroValorNominalRepository repository;


    @BeforeEach
    public void setUp() {
        Field repositoryField = null;
        try {
            repositoryField = BaseService.class.getDeclaredField("repository");
            repositoryField.setAccessible(true);
            repositoryField.set(service, repository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void validDto() {
        IndiceFinanceiroValorNominalDTO dto = new IndiceFinanceiroValorNominalDTO();
        dto.setIndiceFinanceiroId(1L);
        dto.setValor(new BigDecimal("-10"));

        IndiceFinanceiro indice = new IndiceFinanceiro();
        indice.setNegativo(false);

        when(indiceFinanceiroService.getById(1L)).thenReturn(indice);

        assertThrows(Exception.class, () -> service.validDto(dto));

    }

    @Test
    void createOrUpdateAll() {
       /* IndiceFinanceiroValorNominalDTO dto1 = new IndiceFinanceiroValorNominalDTO();
        dto1.setValor(new BigDecimal("100"));
        dto1.setIndiceFinanceiroId(1L);
        dto1.setDataReferencia( LocalDate.of(2024, 2, 1));

        IndiceFinanceiroValorNominalDTO dto2 = new IndiceFinanceiroValorNominalDTO();
        dto2.setValor(new BigDecimal("200"));
        dto2.setIndiceFinanceiroId(1L);
        dto2.setDataReferencia( LocalDate.of(2024, 3, 1));

        List<IndiceFinanceiroValorNominalDTO> dtos = Arrays.asList(dto1, dto2);

        IndiceFinanceiro indice = new IndiceFinanceiro();
        indice.setId(1L);
        indice.setNegativo(false);


        when(indiceFinanceiroService.getById(1L)).thenReturn(indice);
        when(service.createOrUpdate(dto1)).thenReturn(dto1);
        when(service.createOrUpdate(dto2)).thenReturn(dto2);

        List<IndiceFinanceiroValorNominalDTO> result = service.createOrUpdateAll(dtos);

        assertEquals(2, result.size());
        assertEquals(new BigDecimal("100"), result.get(0).getValor());
        assertEquals(new BigDecimal("200"), result.get(1).getValor());
*/
    }

    @Test
    void getListEntreData() {
        IndiceFinanceiro indice = new IndiceFinanceiro();
        indice.setId(1L);
        indice.setMensal(true);

        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        IndiceFinanceiroValorNominal valor1 = new IndiceFinanceiroValorNominal();
        valor1.setDataReferencia(LocalDate.of(2023, 1, 15));

        IndiceFinanceiroValorNominal valor2 = new IndiceFinanceiroValorNominal();
        valor2.setDataReferencia(LocalDate.of(2023, 2, 15));

        List<IndiceFinanceiroValorNominal> valores = Arrays.asList(valor1, valor2);

        when(repository.findByIndiceFinanceiroIdAndDataReferenciaBetween(1L, inicio, fim)).thenReturn(valores);

        List<IndiceFinanceiroValorNominalDTO> result = service.getListEntreData(indice, inicio, fim);

        // Verificações
        assertEquals(4, result.size());
        assertEquals(LocalDate.of(2023, 1, 01), result.get(0).getDataReferencia());

    }

    @Test
    @Override
    public void entityToDTOTest() {
        IndiceFinanceiroValorNominalDTO dto = new IndiceFinanceiroValorNominalDTO();
        dto.setValor(BigDecimal.valueOf(15.5));
        dto.setDataReferencia(ToolDate.intToDate(2024,7,23));

        IndiceFinanceiroValorNominal ob = service.dtoToEntity(dto);

        assertEquals(dto.getValor(), ob.getValor());
        assertEquals(dto.getDataReferencia(), ob.getDataReferencia());

    }

    @Override
    @Test
    public void dtoToEntityTest() {

        IndiceFinanceiroValorNominal ob = new IndiceFinanceiroValorNominal();
        ob.setValor(BigDecimal.valueOf(15.5));
        ob.setDataReferencia(ToolDate.intToDate(2024,7,23));

        IndiceFinanceiroValorNominalDTO dto = service.entityToDTO(ob);

        assertEquals(ob.getValor(), dto.getValor());
        assertEquals(ob.getDataReferencia(), dto.getDataReferencia());
    }
}