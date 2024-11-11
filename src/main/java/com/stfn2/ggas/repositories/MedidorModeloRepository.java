package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorModelo;
import com.stfn2.ggas.domain.dtos.filter.MedidorModeloFilterDTO;

@Repository
public interface MedidorModeloRepository extends BaseRepository<MedidorModelo, MedidorModeloFilterDTO> {

}