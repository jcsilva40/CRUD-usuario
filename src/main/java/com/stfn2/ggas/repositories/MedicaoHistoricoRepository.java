package com.stfn2.ggas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedicaoHistorico;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;

@Repository
public interface MedicaoHistoricoRepository extends BaseRepository<MedicaoHistorico, MedicaoHistoricoFilterDTO> {


   @Query("""
   select
   max(mh.id)
   from MedicaoHistorico mh 
   where mh.pontoConsumo.id = :pontoConsumo
   and mh.medidorInstalacao.id = :medidorInstalacao
   """)
   MedicaoHistorico obterUltimaMedicao(@Param("pontoConsumo") Long pontoConsumo,
                                       @Param("medidorInstalacao") Long medidorInstalacao);


}