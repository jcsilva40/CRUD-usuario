package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoVincularFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.projection.CampanhaDescontoVincularBasicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CampanhaDescontoVincularRepository extends BaseRepository<CampanhaDescontoVincular, CampanhaDescontoVincularFilterDTO> {
    @Query("""
            SELECT 
                cadv.id as id,
                cade.descricao as descricaoCampanha,
                cade.inicioAdesao as inicioAdesao,
                cade.encerramentoAdesao as encerramentoAdesao,
                segp.descricao as segmentoPaiDescricao,
                cont.id as contratoId,
                pocn.cil as cil,
                clie.nome as clienteNome,
                COALESCE(clie.cnpj, clie.cpf) as cnpjOrCpf,
                clie.nomeFantasia as clienteNomeFantasia,
                imov.descricao as imovelNome,
                imov.nip as imovelNip,
                cadv.solicitante.nome as solicitante,
                cadv.dataSolicitacao as dataSolicitacao,
                cadv.aprovador.nome as aprovador,
                cadv.dataAprovacao as dataAprovacao,
                cadv.statusCampanha as statusCampanha,
                cadv.periodoFaturado as periodoFaturado,
                cadv.habilitado as habilitado
            FROM
                CampanhaDescontoVincular cadv
            JOIN
                cadv.campanhaDesconto cade
            INNER JOIN
                cade.segmentoPai segp
            LEFT JOIN
                cadv.imovel imov
            LEFT JOIN
                cadv.contrato cont
            LEFT JOIN
                cont.clienteAssinatura clie
            LEFT JOIN
                cont.contratoPontoConsumo as copc
            LEFT JOIN
                copc.pontoConsumo as pocn
            WHERE
                (cade.descricao LIKE CONCAT('%', :descricao, '%'))
                AND (:habilitado IS NULL OR cadv.habilitado = :habilitado)
                AND (:segmentoPaiId IS NULL OR segp.id = :segmentoPaiId)
                AND (:modalidadeMedicao IS NULL OR cade.modalidadeMedicao = :modalidadeMedicao)
            ORDER BY
                cadv.id ASC
        """)
        Page<CampanhaDescontoVincularBasicProjection> findAll(
            @Param("descricao") String descricao,
            @Param("segmentoPaiId") Long segmentoPaiId,
            @Param("modalidadeMedicao") ModalidadeMedicaoEnum modalidadeMedicao,
            @Param("habilitado") Boolean habilitado,
            Pageable pageable);

    @Query("""
            SELECT 
               vinculo
            FROM
                CampanhaDescontoVincular vinculo
            INNER JOIN vinculo.campanhaDesconto campanha
            LEFT JOIN vinculo.contrato contrato
            LEFT JOIN contrato.contratoPontoConsumo listPontos
            LEFT JOIN listPontos.pontoConsumo pontoC 
            LEFT JOIN vinculo.imovel imov 
            LEFT JOIN imov.pontoConsumos pontoImov
            WHERE 1 = 1
             AND ((pontoC.id = :pIdPontoConsumo) OR (pontoImov.id = :pIdPontoConsumo) )
             AND vinculo.habilitado = true
             AND ((vinculo.statusCampanha in (:StatusCampanhaAprovado,:StatusCampanhaContPendEncerramento)) 
                 or (vinculo.statusCampanha = :StatusCampanhaContEncerrado and ((vinculo.anoMes >= :pPeriodoAtual)
                 or (vinculo.anoMes = :pPeriodoAtual and vinculo.ciclo >= :pCiclo))))
        """)
    CampanhaDescontoVincular obterDescontoAtivoPorPontoConsumo(@Param("pIdPontoConsumo") Long pIdPontoConsumo, @Param(
            "pPeriodoAtual") Integer pPeriodoAtual, @Param("pCiclo") Integer pCiclo,
                                                               @Param("StatusCampanhaAprovado") StatusCampanhaEnum StatusCampanhaAprovado,
                                                               @Param("StatusCampanhaContPendEncerramento") StatusCampanhaEnum StatusCampanhaContPendEncerramento,
                                                               @Param("StatusCampanhaContEncerrado") StatusCampanhaEnum StatusCampanhaContEncerrado );
}