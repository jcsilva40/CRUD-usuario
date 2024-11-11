package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.PrecoMedioPonderado;
import com.stfn2.ggas.domain.dtofilter.PrecoMedioPonderadoFilterDTO;
import com.stfn2.ggas.domain.dtos.PrecoMedioPonderadoDTO;
import com.stfn2.ggas.domain.dtos.basic.PrecoMedioPonderadoBasicDTO;
import com.stfn2.ggas.repositories.PrecoMedioPonderadoRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecoMedioPonderadoService extends BaseService<PrecoMedioPonderado, PrecoMedioPonderadoDTO,
        PrecoMedioPonderadoBasicDTO, PrecoMedioPonderadoFilterDTO, PrecoMedioPonderadoRepository> {

}