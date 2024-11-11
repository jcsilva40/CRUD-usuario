package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorMarca;
import com.stfn2.ggas.domain.dtos.filter.MedidorMarcaFilterDTO;

@Repository
public interface MedidorMarcaRepository extends BaseRepository<MedidorMarca, MedidorMarcaFilterDTO> {

}