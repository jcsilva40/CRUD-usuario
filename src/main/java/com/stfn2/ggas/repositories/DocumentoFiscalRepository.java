package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoFiscal;
import com.stfn2.ggas.domain.dtos.filter.DocumentoFiscalFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoFiscalRepository extends BaseRepository<DocumentoFiscal, DocumentoFiscalFilterDTO> {


   @Query("""
        SELECT  docFiscal
             
        FROM 
           DocumentoFiscal docFiscal
        WHERE 1=1
           AND (docFiscal.fatura.id = :faturaId)
        """)
      DocumentoFiscal obterDocumentoFiscalporIdFatura(@Param("faturaId") Long faturaId);

   @Query("""
        SELECT  docFiscal
             
        FROM 
           DocumentoFiscal docFiscal
           inner join fetch docFiscal.fatura fatura
        WHERE 1=1
           AND (docFiscal.fatura.id = :faturaId)
           AND (docFiscal.tipoOperacao = :tipoOperacaoId)
        """)
   DocumentoFiscal obterDocumentoFiscalPorFatura(@Param("faturaId") Long faturaId,
                                                 @Param("tipoOperacaoId") TipoOperacaoEnum tipoOperacaoId);
}