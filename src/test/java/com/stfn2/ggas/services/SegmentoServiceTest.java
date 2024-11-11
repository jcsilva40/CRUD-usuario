package com.stfn2.ggas.services;

import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.Segmento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SegmentoServiceTest {
    @InjectMocks
    SegmentoService segmentoService;

    @Test
    void beforeSave() {

        // Cria um mock de Segmento, função when usa mock e não objeto real.
        Segmento segment = mock(Segmento.class);

        // Cria mocks de RamoAtividade, no verify() verifica chamadas de métodos em objetos mockados, se for real da erro.
        RamoAtividade ramo1 = mock(RamoAtividade.class);
        RamoAtividade ramo2 = mock(RamoAtividade.class);

        ramo1.setId(1L);
        ramo1.setDescricao("RAMO1");
        ramo2.setId(2L);
        ramo2.setDescricao("RAMO2");


        // Configura a lista de RamosAtividade
        List<RamoAtividade> ramosAtividade = Arrays.asList(ramo1, ramo2);

        when(segment.getRamosAtividade()).thenReturn(ramosAtividade);

        // Chama o método beforeSave
        Segmento result = segmentoService.beforeSave(segment);

        // Verifica se o método setSegmento foi chamado para cada item em RamosAtividade
        verify(ramo1).setSegmento(segment);
        verify(ramo2).setSegmento(segment);

        // Verifica se o resultado é o mesmo segmento passado como argumento
        assertEquals(segment, result);

    }
}