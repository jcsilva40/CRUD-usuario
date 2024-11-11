package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import com.stfn2.ggas.domain.dtos.filter.PressaoFornecimentoFaixaFilterDTO;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PressaoFornecimentoFaixaRepository extends BaseRepository<PressaoFornecimentoFaixa, PressaoFornecimentoFaixaFilterDTO> {
    @Query("""
            SELECT
                prff
            FROM
                PressaoFornecimentoFaixa prff
            INNER JOIN FETCH
                prff.segmento segm
            INNER JOIN FETCH
                prff.unidadePressao unid
            WHERE 1=1
                AND (segm.id = :idSegmento)
                AND (prff.habilitado = true)
            ORDER BY
                unid.descricao,
                prff.medidaMinimo
            """)
    List<PressaoFornecimentoFaixa> findListaPressaoFornecimentoFaixaPorSegmento(@Param("idSegmento") Long idSegmento);
    
}