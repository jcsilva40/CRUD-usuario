package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoCobrancaRepository extends BaseRepository<DocumentoCobranca, DocumentoCobrancaFilterDTO> {

   @Query("""
        SELECT  documentoCobranca
             
        FROM 
           DocumentoCobranca documentoCobranca
           inner join fetch documentoCobranca.cliente cliente
           inner join fetch documentoCobranca.documentoTipo tipoDocumento
           inner join fetch documentoCobranca.cobrancaDebitoSituacao cobrancaDebitoSituacao
           inner join fetch documentoCobranca.itens documentoCobrancaItem
           left join fetch documentoCobrancaItem.faturaGeral faturaGeral
           inner join fetch faturaGeral.faturaAtual faturaAtual
           inner join fetch faturaAtual.listaFaturaItens faturaItem
           inner join fetch faturaItem.rubrica rubrica
           inner join fetch rubrica.financiamentoTipo financiamentoTipo
        WHERE 1=1
           AND faturaAtual.id = :faturaId
           AND documentoCobranca.documentoTipo.id = :tipoDocumentoId
        ORDER BY documentoCobranca.dataEmissao desc
        """)
   List<DocumentoCobranca>consultarDocumentoCobrancaPelaFatura(Long faturaId,
                                        Long tipoDocumentoId);


   @Query("""
        SELECT 
            faturaAtual
        FROM DocumentoCobrancaItem documentoCobrancaItem
           inner join documentoCobrancaItem.faturaGeral faturaGeral
           inner join faturaGeral.faturaAtual faturaAtual 
           inner join documentoCobrancaItem.documentoCobranca documentoCobranca 
        WHERE 1=1
           AND (faturaAtual.id <> :faturaId)
           and (documentoCobranca.id = :documentoCobrancaId)
        
        """)
   List<Fatura> consultarDadosFaturasPorDocumentoCobranca(@Param("faturaId") Long faturaId,
                                                          @Param("documentoCobrancaId")Long documentoCobrancaId);

   @Query("""
        SELECT 
            documentoCobranca
        FROM DocumentoCobranca documentoCobranca
           inner join documentoCobranca.itens documentoCobrancaItem
           left join documentoCobrancaItem.faturaGeral faturaGeral
           inner join faturaGeral.faturaAtual faturaAtual
        WHERE 1=1
           AND (faturaGeral.faturaAtual.id = :faturaId)
           AND (documentoCobranca.documentoTipo.id = :documentoTipoId)
        ORDER BY documentoCobranca.dataEmissao desc   
        
        """)
   List<DocumentoCobranca> consultarDadosDocumentoCobrancaPelaFatura(@Param("faturaId") Long faturaId,
                                                                                    @Param("documentoTipoId")Long documentoTipoId);
}