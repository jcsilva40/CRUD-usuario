package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ServicoPrestado;
import com.stfn2.ggas.domain.dtos.filter.ServicoPrestadoFilterDTO;

@Repository
public interface ServicoPrestadoRepository extends BaseRepository<ServicoPrestado, ServicoPrestadoFilterDTO> {

}
