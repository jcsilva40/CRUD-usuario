package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.ConsumoHistorico;
import com.stfn2.ggas.domain.dtos.ConsumoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.basic.ConsumoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ConsumoHistoricoFilterDTO;
import com.stfn2.ggas.repositories.ConsumoHistoricoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ConsumoHistoricoService extends BaseService<ConsumoHistorico, ConsumoHistoricoDTO, ConsumoHistoricoBasicDTO, ConsumoHistoricoFilterDTO, ConsumoHistoricoRepository> {
    public ConsumoHistorico buscaPorPontoConsumoEdataLeituraHistorico(Long idPontoConsumo, LocalDate dataLeitura) {

        return repository.buscaPorPontoConsumoEdataLeituraHistorico(idPontoConsumo, dataLeitura);
    }
}