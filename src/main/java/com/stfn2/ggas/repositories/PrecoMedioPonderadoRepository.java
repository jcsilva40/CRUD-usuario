package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.PrecoMedioPonderado;
import com.stfn2.ggas.domain.dtofilter.PrecoMedioPonderadoFilterDTO;

@Repository
public interface PrecoMedioPonderadoRepository extends BaseRepository<PrecoMedioPonderado, PrecoMedioPonderadoFilterDTO> {

}