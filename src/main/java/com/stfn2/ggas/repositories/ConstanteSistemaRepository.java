package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ConstanteSistema;
import com.stfn2.ggas.domain.dtos.filter.ConstanteSistemaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstanteSistemaRepository extends BaseRepository<ConstanteSistema, ConstanteSistemaFilterDTO> {

   @Query("""
            SELECT constanteSistema
            FROM
                ConstanteSistema constanteSistema                
            WHERE 1=1
            AND (constanteSistema.nome = :codigo)
            """)
   ConstanteSistema obterConstantePorCodigo(@Param("codigo")String codigo);
}