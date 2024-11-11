package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CorretorPressMaxTransdutor;
import com.stfn2.ggas.domain.dtos.filter.CorretorPressMaxTransdutorFilterDTO;

@Repository
public interface CorretorPressMaxTransdutorRepository extends BaseRepository<CorretorPressMaxTransdutor, CorretorPressMaxTransdutorFilterDTO> {

}