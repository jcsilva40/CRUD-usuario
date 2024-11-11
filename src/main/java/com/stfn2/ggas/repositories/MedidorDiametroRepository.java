package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorDiametro;
import com.stfn2.ggas.domain.dtos.filter.MedidorDiametroFilterDTO;

@Repository
public interface MedidorDiametroRepository extends BaseRepository<MedidorDiametro, MedidorDiametroFilterDTO> {

}