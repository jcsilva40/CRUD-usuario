package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Rubrica;
import com.stfn2.ggas.domain.dtos.filter.RubricaFilterDTO;

import java.util.List;

@Repository
public interface RubricaRepository extends BaseRepository<Rubrica, RubricaFilterDTO> {

    @Query("""
            SELECT 
            new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                r.id,
                r.descricao
            )
            FROM
                Rubrica r
            WHERE 
                r.lancamentoItemContabil.tipoDebitoCredito.id = 89
            """)
    List<ComboDTO> findRubricasCreditoCombo();

    @Query("""
            SELECT 
            new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                r.id,
                r.descricao
            )
            FROM
                Rubrica r
            WHERE 
                r.lancamentoItemContabil.tipoDebitoCredito.id = 90
            """)
    List<ComboDTO> findRubricasDebitoCombo();

    @Query("""
            SELECT r 
            FROM Rubrica r 
            WHERE r.lancamentoItemContabil.tipoDebitoCredito.id = 89
            """)
    List<Rubrica> findRubricasCredito();

    @Query("""
            SELECT r 
            FROM Rubrica r 
            WHERE r.lancamentoItemContabil.tipoDebitoCredito.id = 90
            """)
    List<Rubrica> findRubricasDebito();

}
