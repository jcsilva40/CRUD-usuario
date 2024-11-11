package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoHistoricoDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorOperacaoHistoricoBasicDTO;
import com.stfn2.ggas.repositories.MedidorOperacaoHistoricoRepository;

@Service
public class MedidorOperacaoHistoricoService extends BaseService<MedidorOperacaoHistorico, MedidorOperacaoHistoricoDTO, MedidorOperacaoHistoricoBasicDTO, MedidorOperacaoHistoricoFilterDTO, MedidorOperacaoHistoricoRepository> {

}