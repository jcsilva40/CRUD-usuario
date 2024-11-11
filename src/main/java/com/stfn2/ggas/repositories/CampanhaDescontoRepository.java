package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CampanhaDesconto;
import com.stfn2.ggas.domain.SegmentoPai;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.projection.CampanhaDescontoBasicProjection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CampanhaDescontoRepository extends BaseRepository<CampanhaDesconto, CampanhaDescontoFilterDTO> {
    @Query("""
            SELECT DISTINCT
                cade.id as id,
                cade.descricao as descricao,
                cade.porcentagem as porcentagem,
                segp.id as segmentoPaiId,
                cade.modalidadeMedicao as modalidadeMedicao,
                cade.inicioAdesao as inicioAdesao,
                cade.encerramentoAdesao as encerramentoAdesao,
                cade.periodoVigencia as periodoVigencia,
                (SELECT COUNT(cadv) FROM CampanhaDescontoVincular cadv WHERE cadv.campanhaDesconto = cade AND cadv.statusCampanha IN (com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum.ID_ENT_CONT_APROVADO, com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum.ID_ENT_CONT_PEND_ENCERRAMENTO)) as quantidadeParticipante,
                cade.mensagemFatura as mensagemFatura,
                cade.habilitado as habilitado                   
            FROM
                CampanhaDesconto cade
            INNER JOIN
                cade.segmentoPai segp
            WHERE 1=1
                AND (:descricao IS NULL OR UPPER(cade.descricao) LIKE CONCAT('%', UPPER(:descricao), '%'))
                AND (:habilitado IS NULL OR cade.habilitado = :habilitado)
                AND (:segmentoPaiId IS NULL OR segp.id = :segmentoPaiId)
                AND (:modalidadeMedicao IS NULL OR cade.modalidadeMedicao = :modalidadeMedicao)
            ORDER BY
                cade.id ASC           
            """)
    Page<CampanhaDescontoBasicProjection> findAll(            
            @Param("descricao") String descricao,
            @Param("segmentoPaiId") Long segmentoPaiId,
            @Param("modalidadeMedicao") ModalidadeMedicaoEnum modalidadeMedicao,
            @Param("habilitado") Boolean habilitado,
            Pageable pageable);
    
    @Query("""
            SELECT DISTINCT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                    cade.id,
                    CONCAT(
                        cade.descricao, 
                        ' [INÍCIO: ', TO_CHAR(cade.inicioAdesao, 'dd/MM/yyyy'), 
                        ' FIM: ', TO_CHAR(cade.encerramentoAdesao, 'dd/MM/yyyy'), 
                        '] [', segp.descricao, ']'
                    )
                ),
                cade.encerramentoAdesao
            FROM
                CampanhaDesconto cade
            INNER JOIN
                cade.segmentoPai segp
            WHERE 1=1
                AND (cade.habilitado = true)                    
            ORDER BY
                cade.encerramentoAdesao DESC         
            """)
    List<ComboDTO> findCampanhaDescontoFormatada();
    
    @Query("""
            SELECT           
                segp
            FROM
                CampanhaDesconto cade
            INNER JOIN
                cade.segmentoPai segp
            WHERE 1=1
                AND (cade.id = :campanhaDescontoId)                    
            """)    
    SegmentoPai findSegmentoPaiPorCampanhaDesconto(@Param("campanhaDescontoId") Long campanhaDescontoId);
}