package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CertificadoMedidor;
import com.stfn2.ggas.domain.dtos.filter.CertificadoMedidorFilterDTO;

@Repository
public interface CertificadoMedidorRepository extends BaseRepository<CertificadoMedidor, CertificadoMedidorFilterDTO> {

}