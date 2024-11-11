package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoConvenioFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ArrecadadorContratoConvenioRepository extends BaseRepository<ArrecadadorContratoConvenio, ArrecadadorContratoConvenioFilterDTO> {
    @Query("""
            SELECT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                    arcc.id as id,
                    CASE 
                        WHEN arca.tipoCarteira = com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum.SEM_REGISTRO 
                            THEN CONCAT(arcc.codigoConvenio, ' - CN - ', banc.abreviado)
                        ELSE 
                            CONCAT(arcc.codigoConvenio, ' - CR - ', banc.abreviado)
                    END as descricao
                )
            FROM
                ArrecadadorContratoConvenio arcc, PontoConsumo pocn            
            JOIN 
                arcc.arrecadadorContrato arco
            JOIN  
                arco.arrecadador arre
            JOIN 
                arre.cliente clie
            INNER JOIN
                arcc.arrecadadorCarteiraCobranca arca
            INNER JOIN
                arre.banco banc
            WHERE 1=1
                AND arcc.codigoConvenio = pocn.cil
                AND pocn.id = :pontoConsumoId
                AND (arcc.tipoConvenio = :tipoConvenio)
                AND (clie.id = :clienteId)
            ORDER BY
                arcc.codigoConvenio
            """)
    List<ComboDTO> findAllArrecadadorPorCliente(            
            @Param("tipoConvenio") TipoConvenioBancarioEnum tipoConvenio,
            @Param("clienteId") Long clienteId,
            @Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
        SELECT 
           CASE WHEN COUNT(arcc) > 0 THEN true ELSE false END
        FROM 
           ArrecadadorContratoConvenio arcc
        INNER JOIN
            arcc.arrecadadorContrato arco
        INNER JOIN
            arco.arrecadador arre 
        WHERE (arre.cliente.id =:clienteId)
            AND (arre.banco.id =:bancoId)
            AND (arre.codigoAgente =:cil)
            AND (arcc.tipoConvenio = :tipoConvenio)
        """)
    Boolean obterArrecadadorContratoConvenioPorClienteBancoCil(@Param("clienteId") Long clienteId,
                                                               @Param("bancoId") Long bancoId,
                                                               @Param("cil") String cil,
                                                               @Param("tipoConvenio") TipoConvenioBancarioEnum tipoConvenio);

    @Query("""
        SELECT 
           arcc
        FROM 
           ArrecadadorContratoConvenio arcc
         
        WHERE (1=1)
            AND (arcc.indicadorPadrao = true)
        """)
    ArrecadadorContratoConvenio obterArrecadadorContratoConvenioPadrao();

    @Query("""
         SELECT arcc
         FROM ArrecadadorContratoConvenio arcc
         WHERE arcc.id =
         (SELECT cont_atual.arrecadadorContratoConvenio.id
         FROM DocumentoCobrancaItem doc_cob_item
             INNER JOIN doc_cob_item.faturaGeral fat_geral
             INNER JOIN fat_geral.faturaAtual fat_atual
             INNER JOIN fat_atual.contratoAtual cont_atual
         where 1=1
             AND (cont_atual.contratoSituacao = :situacao)
             AND (doc_cob_item.documentoCobranca.id = :documentoCobrancaId))
        """)
    ArrecadadorContratoConvenio obterArrecadadorContratoConvenioPorDocumentoCobranca(
                @Param("documentoCobrancaId") Long documentoCobrancaId,
                @Param("situacao") ContratoSituacaoEnum situacao
    );
}