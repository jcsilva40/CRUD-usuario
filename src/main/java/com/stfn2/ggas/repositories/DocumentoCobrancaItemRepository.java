package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoCobrancaItem;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaItemFilterDTO;

@Repository
public interface DocumentoCobrancaItemRepository extends BaseRepository<DocumentoCobrancaItem, DocumentoCobrancaItemFilterDTO> {

}