package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ParametroSistema;
import com.stfn2.ggas.domain.dtos.filter.ParametroSistemaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroSistemaRepository extends BaseRepository<ParametroSistema, ParametroSistemaFilterDTO> {

   @Query("""
        SELECT  parametroSistema
             
        FROM 
           ParametroSistema parametroSistema
        WHERE 1=1
           AND (parametroSistema.codigo = :codigo)
        """)
   ParametroSistema obterParametroPorCodigo(@Param("codigo")String codigo);
}