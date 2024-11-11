package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoGrupoRotaImpress;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoRotaImpressDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoRotaImpressFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoGrupoRotaImpressBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoGrupoRotaImpressRepository;

@Service
public class FaturamentoGrupoRotaImpressService extends BaseService<FaturamentoGrupoRotaImpress, FaturamentoGrupoRotaImpressDTO, FaturamentoGrupoRotaImpressBasicDTO, FaturamentoGrupoRotaImpressFilterDTO, FaturamentoGrupoRotaImpressRepository> {

}