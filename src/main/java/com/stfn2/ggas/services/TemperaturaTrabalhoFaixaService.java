package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.TemperaturaTrabalhoFaixa;
import com.stfn2.ggas.domain.dtos.TemperaturaTrabalhoFaixaDTO;
import com.stfn2.ggas.domain.dtos.filter.TemperaturaTrabalhoFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TemperaturaTrabalhoFaixaBasicDTO;
import com.stfn2.ggas.repositories.TemperaturaTrabalhoFaixaRepository;

@Service
public class TemperaturaTrabalhoFaixaService extends BaseService<TemperaturaTrabalhoFaixa, TemperaturaTrabalhoFaixaDTO, TemperaturaTrabalhoFaixaBasicDTO, TemperaturaTrabalhoFaixaFilterDTO, TemperaturaTrabalhoFaixaRepository> {

}