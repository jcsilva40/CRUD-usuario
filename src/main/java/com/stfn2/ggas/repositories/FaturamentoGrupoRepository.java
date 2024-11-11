package com.stfn2.ggas.repositories;

import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoFilterDTO;

import java.util.List;

@Repository
public interface FaturamentoGrupoRepository extends BaseRepository<FaturamentoGrupo, FaturamentoGrupoFilterDTO> {
    @Query("""
            SELECT new com.stfn2.ggas.services.componentes.faturamentogruporota.vo.FaturamentoGrupoVO 
                (
                fagr.id,
                fagr.descricao,
                fagr.anoMesFaturamento as anoMes,
                fagr.numeroCiclo as ciclo
                )
            FROM
                FaturamentoGrupo fagr
            """)
    List<FaturamentoGrupoVO> listaFaturamentoGrupo();
}
