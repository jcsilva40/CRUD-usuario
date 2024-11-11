package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.SuperMedicaoHoraria;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoHorariaFilterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperMedicaoHorariaRepository extends BaseRepository<SuperMedicaoHoraria, SuperMedicaoHorariaFilterDTO> {

}
