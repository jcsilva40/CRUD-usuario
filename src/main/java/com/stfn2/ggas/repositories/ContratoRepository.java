package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.ContratoPontoConsumo;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.dtos.filter.ContratoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import com.stfn2.ggas.domain.projection.ContratoBasicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratoRepository extends BaseRepository<Contrato, ContratoFilterDTO> {
    
    @Query("""
            SELECT DISTINCT
                    cont.id as id,
                    clie.id as idCliente,
                    clie.nome as nomeCliente,
                    pocn.id as idPontoConsumo,
                    pocn.descricao as descricaoPontoConsumo,
                    cont.numero as numeroContrato,
                    COALESCE(clie.cpf, clie.cnpj) as cpfCnpjCliente,
                    cont.contratoSituacao as contratoSituacao,                    
                    cont.dataAssinatura as dataAssinatura,
                    cont.dataVencimentoObrigacoes as dataVencimento
            FROM
                Contrato cont
            JOIN
                cont.contratoCliente cocl
            JOIN
                cocl.cliente clie
            JOIN
                cont.contratoPontoConsumo copc
            JOIN
                copc.pontoConsumo pocn
            JOIN
                pocn.imovel imov
            WHERE 1=1
                AND (:cliente IS NULL OR UPPER(clie.nome) LIKE CONCAT('%',UPPER(:cliente),'%'))
                AND (:nip IS NULL OR (imov.nip = :nip))
                AND (:cil IS NULL OR (pocn.cil = :cil))
                AND (:habilitado IS NULL or cont.habilitado = :habilitado) 
            ORDER BY
                cont.id DESC
            """)
    Page<ContratoBasicProjection> findAll(            
            @Param("cliente") String cliente,
            @Param("nip") Integer nip,
            @Param("cil") String cil,
            @Param("habilitado") Boolean habilitado,
            Pageable pageable);
    
    @Query("""
            SELECT DISTINCT
                    cont
            FROM
                Contrato cont                
            WHERE 1=1
            AND (:descricao IS NULL OR UPPER(cont.descricaoContrato) LIKE CONCAT('%',UPPER(:descricao),'%'))
            AND (:habilitado IS NULL or cont.habilitado = :habilitado)
            """)
    Page<Contrato> findAllCompleto(            
            @Param("descricao") String descricao,
            @Param("habilitado") Boolean habilitado,
            Pageable pageable);
    
    @Query("""
            SELECT 
                COALESCE(MAX(cont.numero), 0) + 1 
            FROM 
                Contrato cont 
            WHERE 
                cont.anoContrato = :pAno
           """)
    Integer getNextNumberOfContrato(@Param("pAno") Integer pAno);

    @Query("""
            SELECT
                contrato
            FROM
                Contrato contrato            
            WHERE 1=1
                AND contrato.clienteAssinatura.id = :idCliente
                AND contrato.habilitado = true
            """)
    List<Contrato> obterListaContratoPorCliente(@Param("idCliente")Long idCliente);
    
    @Query("""
            SELECT 
                CASE WHEN COUNT(cont) > 0 THEN true ELSE false END
            FROM
                Contrato cont
            JOIN 
                cont.contratoPontoConsumo copc
            WHERE
                copc.pontoConsumo.id = :pontoConsumoId
                AND cont.contratoSituacao IN (com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ATIVO, com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ALTERADO)                
            """)
    Boolean existeContratoEmAberto(@Param("pontoConsumoId") Long pontoConsumoId);
    
    @Query("""
            SELECT 
                cont
            FROM
                Contrato cont
            JOIN 
                cont.contratoPontoConsumo copc
            WHERE
                copc.pontoConsumo.id = :pontoConsumoId
                AND cont.contratoSituacao IN (com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ATIVO, com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ALTERADO)
            """)
    List<Contrato> contratoExistenteParaOPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
            SELECT 
                contratoPonto
            FROM
                ContratoPontoConsumo contratoPonto
                inner join fetch contratoPonto.pontoConsumo pontoConsumo
                inner join fetch contratoPonto.contrato contrato 
                left join fetch contrato.arrecadadorContratoConvenio arrecadadorContratoConvenio
                inner join fetch pontoConsumo.segmento segmento
                inner join fetch contrato.clienteAssinatura cliente
                left join fetch contratoPonto.contrato.listaFatura listaFatura
                inner join fetch contrato.contratoSituacao situacaoContrato
                left join fetch contratoPonto.medicaoTipo tipoMedicao
                left join fetch contrato.multaRecisoria multaRecisoria
                left join fetch contratoPonto.unidadeVazaoMaximaInstantanea unidadeVazaoMaximaInstantanea
                        
            WHERE  contratoPonto.pontoConsumo.id = :pontoConsumoId
              and contratoPonto.contrato.contratoSituacao = :situacao
              and contratoPonto.contrato.chavePrimariaPrincipal is null         
            """)
    ContratoPontoConsumo obterContratoAtivoPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId,
                                                        @Param("situacao") ContratoSituacaoEnum situacao);

    @Query("""
            SELECT 
                contratoPonto
            FROM
              ContratoPontoConsumo contratoPonto
              inner join fetch contratoPonto.contrato 
              inner join fetch contratoPonto.periodicidade 
              inner join fetch contratoPonto.contrato.clienteAssinatura 
              inner join fetch contratoPonto.pontoConsumo 
              inner join fetch contratoPonto.pontoConsumo.imovel 
              left join fetch contratoPonto.unidadePressao 
                        
            WHERE  contratoPonto.pontoConsumo.id = :pontoConsumoId 
              and contratoPonto.contrato.chavePrimariaPrincipal is null 
              and contratoPonto.contrato.dataAssinatura < :dataEmissao 
            
            order by contratoPonto.contrato.dataAssinatura desc, contratoPonto.contrato.ultimaAlteracao desc    
            """)
    List<ContratoPontoConsumo> consultarContratoPontoConsumoPorPontoConsumoAntesDataEmissao(@Param("pontoConsumoId") Long pontoConsumoId,
                                                                                            @Param("dataEmissao") LocalDate dataEmissao);

    @Query("""
            SELECT 
                indiceFinanceiroValorNominal
            FROM
              IndiceFinanceiroValorNominal indiceFinanceiroValorNominal
                                      
            WHERE  indiceFinanceiroValorNominal.indiceFinanceiro.id = :indiceFinanceiroId 
              and (indiceFinanceiroValorNominal.dataReferencia >= :dataReferenciaInicial) 
              and (indiceFinanceiroValorNominal.dataReferencia <= :dataReferenciaFinal) 
            
            order by indiceFinanceiroValorNominal.dataReferencia asc     
            """)
    List<IndiceFinanceiroValorNominal> listaValorNominalIndiceFinanceiro(
            @Param("indiceFinanceiroId") Long indiceFinanceiroId,
            @Param("dataReferenciaInicial") LocalDate dataReferenciaInicial,
            @Param("dataReferenciaFinal") LocalDate dataReferenciaFinal);
    
    @Query("""
        SELECT DISTINCT
            new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                cont.id,
                CONCAT(
                    pocn.cil, ' - ',
                    cont.clienteAssinatura.nome, ' - ',
                    COALESCE(cont.clienteAssinatura.cnpj, cont.clienteAssinatura.cpf), ' - ',
                    pocn.descricao
                )
            )
        FROM
            Contrato cont
        LEFT JOIN
            cont.contratoPontoConsumo copc
        LEFT JOIN
            copc.pontoConsumo pocn
        LEFT JOIN
            pocn.segmento segm
        LEFT JOIN
           segm.segmentoPai segp
        WHERE
            1=1
            AND ((cont.id NOT IN (
                SELECT cadv.contrato.id
                FROM CampanhaDescontoVincular cadv
                WHERE cadv.habilitado = true
                AND cadv.contrato IS NOT NULL
                AND cadv.statusCampanha != com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum.ID_ENT_CONT_ENCERRADO
            ))
            OR (:contratoId IS NOT NULL AND cont.id = :contratoId))
            AND (:segmentoPaiId IS NULL OR segp.id = :segmentoPaiId)
            AND (cont.contratoSituacao IN (com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ATIVO, com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum.ALTERADO))
            AND (cont.dataLiberacaoGas IS NOT NULL)
        ORDER BY
            cont.id
    """)
    List<ComboDTO> findContratosFiltradosCade(
        @Param("contratoId") Long contratoId,
        @Param("segmentoPaiId") Long segmentoPaiId        
    );
    
}