package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Empresa;
import com.stfn2.ggas.domain.dtos.filter.EmpresaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends BaseRepository<Empresa, EmpresaFilterDTO> {

   @Query("""
        SELECT empresa
        FROM Empresa empresa
        WHERE 1=1
           AND empresa.principal = true
        """)
   Empresa obterEmpresaPrincipal();
}
