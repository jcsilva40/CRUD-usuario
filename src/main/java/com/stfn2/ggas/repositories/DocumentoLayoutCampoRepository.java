package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoLayoutCampo;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutCampoFilterDTO;

@Repository
public interface DocumentoLayoutCampoRepository extends BaseRepository<DocumentoLayoutCampo, DocumentoLayoutCampoFilterDTO> {

}