package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeFilterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface RamoAtividadeRepository extends BaseRepository<RamoAtividade, RamoAtividadeFilterDTO> {

}
