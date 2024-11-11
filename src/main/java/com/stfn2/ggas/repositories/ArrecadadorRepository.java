package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Arrecadador;
import com.stfn2.ggas.domain.ArrecadadorCarteiraCobranca;
import com.stfn2.ggas.domain.ArrecadadorContrato;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorFilterDTO;
import com.stfn2.ggas.domain.projection.ArrecadadorBasicProjection;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrecadadorRepository extends BaseRepository<Arrecadador, ArrecadadorFilterDTO> {

    @Query("""
        
        SELECT arre
          FROM ArrecadadorContrato arre 
          WHERE (arre.arrecadador.id =:idArrecadador)
          
       
        """)
    ArrecadadorContrato obterArrecadadorContratoPorArrecadador(@Param("idArrecadador") Long idArrecadador);

    @Query("""
        
        SELECT arrecadador
          FROM ArrecadadorCarteiraCobranca arrecadador 
          WHERE (arrecadador.arrecadador.id =:idArrecadador)
          
       
        """)
    ArrecadadorCarteiraCobranca obterArrecadadorCarteiraCobrancaPorArrecadador(@Param("idArrecadador") Long idArrecadador);

    @Query("""

        SELECT ac
          FROM ArrecadadorContratoConvenio ac
          WHERE (ac.arrecadadorContrato.id =:idArrecadadorContrato)


        """)
    ArrecadadorContratoConvenio obterArrecadadorContratoConvenio(@Param("idArrecadadorContrato") Long idArrecadadorContrato);

    @Query("""

        SELECT ac
        FROM ArrecadadorContratoConvenio ac
          inner join fetch ac.arrecadadorContrato
          inner join fetch ac.arrecadadorContrato.arrecadador 
          inner join fetch ac.arrecadadorContrato.arrecadador.banco 
          inner join fetch ac.contaConvenio 
          inner join fetch ac.contaConvenio.agencia 
          left join fetch ac.arrecadadorCarteiraCobranca
        WHERE (1=1)
          AND ac.habilitado = true 
          AND ac.arrecadadorContrato.dataFimContrato is null
          AND ac.indicadorPadrao = true

        """)
    ArrecadadorContratoConvenio obterArrecadadorContratoConvenioParaBoletoBancario();

    @Query("""
        
        SELECT convenio.arrecadadorCarteiraCobranca
        FROM ArrecadadorContratoConvenio convenio 
        WHERE (convenio.id = :arrecadadorConvenioId)
        """)
    ArrecadadorCarteiraCobranca obterCarteiraCobrancaDeConvenio(@Param("arrecadadorConvenioId") Long arrecadadorConvenioId);

    @Query("""
            SELECT DISTINCT
                    arre.id as id,
                    arre.codigoAgente as codigoAgente,
                    clie.id as idCliente,
                    clie.nome as nomeCliente,
                    banc.id as idBanco,
                    banc.nome as nomeBanco
            FROM
                Arrecadador arre
            JOIN
                arre.cliente clie
            JOIN
                arre.banco banc
            
            WHERE 1=1
                AND (:cliente IS NULL OR UPPER(clie.nome) LIKE CONCAT('%',UPPER(:cliente),'%'))
                AND (:banco IS NULL OR UPPER(banc.nome) LIKE CONCAT('%',UPPER(:banco),'%'))
                AND (:codigoAgente IS NULL OR UPPER(codigoAgente) LIKE CONCAT('%',UPPER(:codigoAgente),'%'))
                AND (:habilitado IS NULL or arre.habilitado = :habilitado)
            ORDER BY
                arre.id DESC
            """)

    Page<ArrecadadorBasicProjection> findAll(
            @Param("cliente") String cliente,
            @Param("banco") String banco,
            @Param("codigoAgente") String codigoAgente,
            @Param("habilitado") Boolean habilitado,
            Pageable pageable);
    }