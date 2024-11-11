package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoImpressaoLayout;
import com.stfn2.ggas.domain.dtos.filter.DocumentoImpressaoLayoutFilterDTO;
import com.stfn2.ggas.domain.enumTypes.TipoDocumentoImpressoEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoImpressaoLayoutRepository extends BaseRepository<DocumentoImpressaoLayout, DocumentoImpressaoLayoutFilterDTO> {

   @Query("""
            SELECT documentoImpressaoLayout
            FROM
                DocumentoImpressaoLayout documentoImpressaoLayout                
            WHERE 1=1
            AND (documentoImpressaoLayout.tipoDocumentoImpressao= :tipoDocumentoImpressoId)
            AND (documentoImpressaoLayout.habilitado = true)
            order by documentoImpressaoLayout.nome asc
            """)
   DocumentoImpressaoLayout consultarDocumentoImpressaoLayout(@Param("tipoDocumentoImpressoId") TipoDocumentoImpressoEnum tipoDocumentoImpressoId);
}