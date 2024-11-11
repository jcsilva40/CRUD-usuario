package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SuperMedicaoDiariaRepository extends BaseRepository<SuperMedicaoDiaria, SuperMedicaoDiariaFilterDTO> {
    @Query("""
            SELECT
                sumd.cil
            FROM
                SuperMedicaoDiaria sumd
            WHERE
                sumd.cil IN :listaCilsPontoConsumo 
                AND sumd.numeroCiclo = :ciclo
                AND sumd.anoMes = :anoMes
            """)
    List<String> listaIdsPontoConsumoSemSuperMedicaoDiaria(@Param Integer anoMes, @Param Integer ciclo, @Param List<String> listaCilsPontoConsumo);

    @Query("""
            SELECT
               sumd
            FROM
                SuperMedicaoDiaria sumd
            WHERE
                sumd.cil = :cil
                AND sumd.dataRealizacaoLeitura = :dataRealizacaoLeitura
            """)
    SuperMedicaoDiaria buscaPorPontoDeConsumo(@Param String cil, @Param LocalDateTime dataRealizacaoLeitura);

}
