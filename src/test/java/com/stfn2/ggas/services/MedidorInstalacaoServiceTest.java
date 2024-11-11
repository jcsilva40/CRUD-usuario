package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Medidor;
import com.stfn2.ggas.domain.MedidorInstalacao;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.dtos.MedidorInstalacaoDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import com.stfn2.ggas.repositories.MedidorInstalacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedidorInstalacaoServiceTest {
    @InjectMocks
    private MedidorInstalacaoService medidorInstalacaoService;

    @Mock
    protected PontoConsumoService servicePontoConsumo;

    @Mock
    private MedidorService serviceMedidor;

    @Mock
    protected MedidorOperacaoHistoricoService serviceMedidorOpHistorico;
    @Mock
    private MedidorInstalacaoRepository repository;


    @BeforeEach
    public void setUp() {
        Field repositoryField = null;
        try {
            repositoryField = BaseService.class.getDeclaredField("repository");
            repositoryField.setAccessible(true);
            repositoryField.set(medidorInstalacaoService, repository);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    //Verifica se o metodo da certo quando isRetirada() = true
   // @Test
    void validDto_whenRetirada() {
        MedidorInstalacaoDTO dto = new MedidorInstalacaoDTO();
        dto.setId(1L);
        dto.setRetirada(true);
        dto.setMedidorId(1L);
        dto.setPontoConsumoId(1L);

        Medidor medidor = new Medidor();
        medidor.setId(10L);
        PontoConsumo pontoConsumo = mock(PontoConsumo.class);

        when(serviceMedidor.getById(1L)).thenReturn(medidor);
        when(servicePontoConsumo.getById(1L)).thenReturn(pontoConsumo);

        medidorInstalacaoService.validDto(dto);

        assertEquals(medidor.getMedidorSituacaoEnum(), MedidorSituacaoEnum.DESMOBILIZADO);

        verify(pontoConsumo).setMedidorInstalacao(null);
    }

    //quando medidor ta instalado e isRetirada() é false
   // @Test
    void validDto_whenNotRetiradaAndMedidorAlreadyInstalled() {
        MedidorInstalacaoDTO dto = new MedidorInstalacaoDTO();
        dto.setId(1L);
        dto.setRetirada(false);
        dto.setMedidorId(1L);
        dto.setPontoConsumoId(1L);

        Medidor medidor = mock(Medidor.class);
        when(serviceMedidor.getById(1L)).thenReturn(medidor);
        when(medidor.getMedidorSituacaoEnum()).thenReturn(MedidorSituacaoEnum.INSTALADO);

        assertThrows(RuntimeException.class, () -> medidorInstalacaoService.validDto(dto));
    }

    //quando ja tem instalacao associada neel
    //@Test
    void validDto_whenPontoConsumoAlreadyHasInstalacao() {
        MedidorInstalacaoDTO dto = new MedidorInstalacaoDTO();
        dto.setId(0L);
        dto.setRetirada(false);
        dto.setMedidorId(1L);
        dto.setPontoConsumoId(1L);

        PontoConsumo pontoConsumo = mock(PontoConsumo.class);
        MedidorInstalacao medidorInstalacao = mock(MedidorInstalacao.class);
        when(servicePontoConsumo.getById(1L)).thenReturn(pontoConsumo);
        when(pontoConsumo.getMedidorInstalacao()).thenReturn(medidorInstalacao);

        assertThrows(RuntimeException.class, () -> medidorInstalacaoService.validDto(dto));
    }


    @Test
    void beforeSave() {
        MedidorInstalacao entity = new MedidorInstalacao();
        PontoConsumo pontoConsumo = new PontoConsumo();
        pontoConsumo.setId(1L);
        entity.setPontoConsumo(pontoConsumo);

        MedidorInstalacao existingInstalacao = new MedidorInstalacao();
        Medidor medidor = new Medidor();
        existingInstalacao.setMedidor(medidor);

        List<MedidorInstalacao> listaInstalacoes = Arrays.asList(existingInstalacao);

        when(repository.obterInstalacaoPorPontoConsumo(1L)).thenReturn(listaInstalacoes);

        MedidorInstalacao result = medidorInstalacaoService.beforeSave(entity);

        assertNotNull(result);
        assertFalse(existingInstalacao.getHabilitado());
        assertEquals(MedidorSituacaoEnum.DESMOBILIZADO, existingInstalacao.getMedidor().getMedidorSituacaoEnum());

        verify(serviceMedidor, times(1)).save(existingInstalacao.getMedidor());
        verify(repository, times(1)).saveAll(listaInstalacoes);
    }

    @Test
    void afterSave() {
        MedidorInstalacao instalacao = new MedidorInstalacao();

        PontoConsumo pc = new PontoConsumo();
        pc.setId(5L);

        Medidor medidor = new Medidor();
        medidor.setId(10L);
        instalacao.setMedidor(medidor);
        instalacao.setPontoConsumo(pc);

        when(servicePontoConsumo.getById(pc.getId())).thenReturn(pc);
        when(serviceMedidor.getById(medidor.getId())).thenReturn(medidor);

        when(serviceMedidorOpHistorico.save(any())).thenReturn(new MedidorOperacaoHistorico());

        MedidorInstalacao response = medidorInstalacaoService.afterSave(instalacao);

        assertEquals(instalacao, response.getPontoConsumo(). getMedidorInstalacao() );
        assertEquals(MedidorSituacaoEnum.INSTALADO, response.getMedidor().getMedidorSituacaoEnum() );
    }
}