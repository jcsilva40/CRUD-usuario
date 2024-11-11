package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Unidade;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFilterDTO;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UnidadeRepository extends BaseRepository<Unidade, UnidadeFilterDTO> {
    @Query("""
            SELECT DISTINCT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(    
                    unid.id as id,
                    unid.descricao as descricao
                )
            FROM
                Unidade unid                
            WHERE 1=1
            AND (unid.unidadeClasse.id = :idUnidadeClasse)
            AND (unid.habilitado = true)
            """)
    List<ComboDTO> findAllPorUnidadeClasse(@Param("idUnidadeClasse") Long idEntidadeClasse);
    
    @Query("""
            SELECT DISTINCT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(    
                    unid.id as id,
                    unid.abreviado as descricao
                )
            FROM
                Unidade unid                
            WHERE 1=1
            AND (unid.unidadeClasse.id = :idUnidadeClasse)
            AND (unid.habilitado = true)
            """)
    List<ComboDTO> findAllPorUnidadeClasseAbreviado(@Param("idUnidadeClasse") Long idEntidadeClasse);
}