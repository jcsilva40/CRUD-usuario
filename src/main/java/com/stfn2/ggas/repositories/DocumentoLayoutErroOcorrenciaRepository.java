package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoLayoutErroOcorrencia;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutErroOcorrenciaFilterDTO;

@Repository
public interface DocumentoLayoutErroOcorrenciaRepository extends BaseRepository<DocumentoLayoutErroOcorrencia, DocumentoLayoutErroOcorrenciaFilterDTO> {

}