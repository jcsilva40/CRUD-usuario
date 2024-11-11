package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;
import com.stfn2.ggas.domain.dtos.filter.TarifaFaixaDescontoFilterDTO;

@Repository
public interface TarifaFaixaDescontoRepository extends BaseRepository<TarifaFaixaDesconto, TarifaFaixaDescontoFilterDTO> {

}
