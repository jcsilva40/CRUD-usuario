package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoLayout;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoLayoutRepository extends BaseRepository<DocumentoLayout, DocumentoLayoutFilterDTO> {

   @Query("""
           SELECT  documentoLayout
                
           FROM 
              DocumentoLayout documentoLayout
              inner join fetch documentoLayout.formaArrecadacao
           WHERE 1=1
              AND (documentoLayout.id IN (select leiaute.id from ArrecadadorContratoConvenio convenio inner join convenio.leiaute leiaute where convenio.id = :arrecadadorContratoConvenioId))
           """)
   DocumentoLayout obterDocumentoLayoutPorConvenio(@Param("arrecadadorContratoConvenioId") Long arrecadadorContratoConvenioId);
}