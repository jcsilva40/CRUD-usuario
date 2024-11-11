package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RamoAtividadeSubstituicaoTributaria;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeSubstituicaoTributariaFilterDTO;

@Repository
public interface RamoAtividadeSubstituicaoTributariaRepository extends BaseRepository<RamoAtividadeSubstituicaoTributaria, RamoAtividadeSubstituicaoTributariaFilterDTO> {

}