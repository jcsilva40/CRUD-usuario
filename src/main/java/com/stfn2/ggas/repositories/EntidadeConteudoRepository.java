package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.dtos.filter.EntidadeConteudoFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.EntidadeConteudo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EntidadeConteudoRepository extends BaseRepository<EntidadeConteudo, EntidadeConteudoFilterDTO> {
    @Query("""
            SELECT DISTINCT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(    
                    enco.id as id,
                    enco.descricao as descricao
                )
            FROM
                EntidadeConteudo enco                
            WHERE 1=1
            AND (enco.entidadeClasse.id = :idEntidadeClasse)
            AND (enco.habilitado = true)
            """)
    List<ComboDTO> findAllPorEntidadeClasse(@Param("idEntidadeClasse") Long idEntidadeClasse);
    
    @Query("""
            SELECT DISTINCT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(    
                    enco.id as id,
                    enco.abreviado as descricao
                )
            FROM
                EntidadeConteudo enco                
            WHERE 1=1
            AND (enco.entidadeClasse.id = :idEntidadeClasse)
            AND (enco.habilitado = true)
            """)
    List<ComboDTO> findAllPorEntidadeClasseAbreviado(@Param("idEntidadeClasse") Long idEntidadeClasse);
}
