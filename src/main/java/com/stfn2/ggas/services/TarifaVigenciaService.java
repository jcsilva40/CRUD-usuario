package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Tarifa;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.dtos.TarifaVigenciaDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaVigenciaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFilterDTO;
import com.stfn2.ggas.repositories.TarifaRepository;
import com.stfn2.ggas.repositories.TarifaVigenciaRepository;
import com.stfn2.ggas.services.componentes.vo.TarifaVigenciaAtualVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarifaVigenciaService extends BaseService<TarifaVigencia, TarifaVigenciaDTO, TarifaVigenciaBasicDTO, TarifaVigenciaFilterDTO, TarifaVigenciaRepository> {

   @Autowired
   private TarifaRepository tarifaRepository;

   public List<TarifaVigencia> obterTarifasVigenciaAtuais() {
      List<Tarifa> tarifas = new ArrayList<>();
      List<TarifaVigencia> tarifasVigentes = new ArrayList<>();

      tarifas = tarifaRepository.findAll();

      for(Tarifa tarifa: tarifas){
         tarifasVigentes.add(repository.obterTarifaVigenciaAtualporTarifa(tarifa.getId()));
      }

      return tarifasVigentes;
   }

   public List<TarifaVigenciaAtualVO> obterTarifasVigenciaAtuaisVO() {

      List<TarifaVigencia> tarifasVigentes = this.obterTarifasVigenciaAtuais();
      List<TarifaVigenciaAtualVO> tarifasVigenciaAtualVOs = new ArrayList<>();

      for(TarifaVigencia tavi: tarifasVigentes){
         tarifasVigenciaAtualVOs.add(new TarifaVigenciaAtualVO(tavi.getTarifa().getDescricao(), tavi.getDataVigencia()));
      }

      return tarifasVigenciaAtualVOs;
   }

}

