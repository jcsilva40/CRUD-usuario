package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.Tarifa;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.dtos.TarifaDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaFilterDTO;
import com.stfn2.ggas.repositories.TarifaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TarifaService extends BaseService<Tarifa, TarifaDTO, TarifaBasicDTO, TarifaFilterDTO, TarifaRepository> {

    Map<String, Object> cacheTarifaVigencia = new HashMap<>();

    public List<ComboDTO> findTarifaPorPontoConsumo(Long pontoConsumoId){
        return this.repository.findTarifaPorPontoConsumo(pontoConsumoId);
    }

    public TarifaVigencia obterTarifaVigencia(Long chaveTarifa, LocalDate dataTarifaVigente) {
        if (!getCacheTarifaVigencia().containsKey(String.valueOf(chaveTarifa))) {
            this.updateCache(chaveTarifa, repository.consultarDadosTarifaVigenciaAnteriorMaisRecente(chaveTarifa, dataTarifaVigente));
        }

        return (TarifaVigencia) getCacheTarifaVigencia().getOrDefault(String.valueOf(chaveTarifa), null);
    }

    public Map<String, Object> getCacheTarifaVigencia() {
        return cacheTarifaVigencia;
    }

    public void updateCache(Long chaveTarifa, TarifaVigencia tarifaVigencia) {
        if (tarifaVigencia != null) {
            this.cacheTarifaVigencia.put(String.valueOf(chaveTarifa), tarifaVigencia);
        }
    }

    @Override
    public void validDto(TarifaDTO dto) {
        dto.getVigencias().get(0).setTarifa(new Tarifa(dto.getId()));
    }
}

