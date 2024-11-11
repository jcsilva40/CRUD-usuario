package com.stfn2.ggas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Cep;
import com.stfn2.ggas.domain.dtos.filter.CepFilterDTO;

@Repository
public interface CepRepository extends BaseRepository<Cep, CepFilterDTO> {

    @Query("""
            SELECT
            cep
            FROM Cep cep
            WHERE cep.id = :cep
            """)
    public Cep findByCep(@Param("cep") Long cep);
}
