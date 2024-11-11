package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Medidor;
import com.stfn2.ggas.domain.dtos.MedidorDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorFilterDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidorRepository extends BaseRepository<Medidor, MedidorFilterDTO> {

    @Query("""
            SELECT
                m
            FROM
                Medidor m
            WHERE 1=1
                AND (m.medidorModelo = :medidorSituacaoId)
            ORDER BY
                m.descricao
            """)

    List<Medidor> findListaMedidorInstalacao(@Param("medidorSituacaoId") Long medidorSituacaoId);

    @Query("""
            SELECT
                new com.stfn2.ggas.domain.dtos.MedidorDTO(
                    m
                )
            FROM
                Medidor m
            WHERE 1=1
                AND (m.medidorSituacaoEnum <> :medidorSituacao)
            ORDER BY
                m.descricao
            """)
    List<MedidorDTO> obterListaMedidoresDisponiveis(@Param("medidorSituacao") MedidorSituacaoEnum medidorSituacao);
}