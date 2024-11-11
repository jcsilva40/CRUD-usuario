package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.DocumentoLayoutEnvioOcorrencia;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutEnvioOcorrenciaFilterDTO;

@Repository
public interface DocumentoLayoutEnvioOcorrenciaRepository extends BaseRepository<DocumentoLayoutEnvioOcorrencia, DocumentoLayoutEnvioOcorrenciaFilterDTO> {

}