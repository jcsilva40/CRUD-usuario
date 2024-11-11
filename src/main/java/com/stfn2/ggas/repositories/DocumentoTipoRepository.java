package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoTipo;
import com.stfn2.ggas.domain.dtos.filter.DocumentoTipoFilterDTO;

@Repository
public interface DocumentoTipoRepository extends BaseRepository<DocumentoTipo, DocumentoTipoFilterDTO> {

}