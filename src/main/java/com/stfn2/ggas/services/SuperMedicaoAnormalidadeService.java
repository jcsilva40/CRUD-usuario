package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;
import com.stfn2.ggas.domain.dtos.TarifaFaixaDescontoDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaFaixaDescontoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaFaixaDescontoFilterDTO;
import com.stfn2.ggas.repositories.TarifaFaixaDescontoRepository;
import org.springframework.stereotype.Service;

@Service
public class SuperMedicaoAnormalidadeService extends BaseService<TarifaFaixaDesconto, TarifaFaixaDescontoDTO, TarifaFaixaDescontoBasicDTO, TarifaFaixaDescontoFilterDTO, TarifaFaixaDescontoRepository> {

}

