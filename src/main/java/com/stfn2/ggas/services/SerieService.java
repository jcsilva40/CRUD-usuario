package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.Serie;
import com.stfn2.ggas.domain.dtos.SerieDTO;
import com.stfn2.ggas.domain.dtos.basic.SerieBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.SerieFilterDTO;
import com.stfn2.ggas.repositories.SerieRepository;

@Service
public class SerieService extends BaseService<Serie, SerieDTO, SerieBasicDTO, SerieFilterDTO, SerieRepository> {

}