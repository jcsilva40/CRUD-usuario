package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContratoPontoConsumo;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoPontoConsumoRepository extends BaseRepository<ContratoPontoConsumo, ContratoPontoConsumoFilterDTO> {

   @Query("""
            SELECT contratoPonto
            FROM
                ContratoPontoConsumo contratoPonto                
            WHERE 1=1
            AND (contratoPonto.pontoConsumo.id = :pontoConsumoId)
            AND (contratoPonto.contrato.contratoSituacao = 1l)
            AND (contratoPonto.contrato.chavePrimariaPrincipal is null)
            """)
   ContratoPontoConsumo obterContratoAtivoPontoConsumo(@Param("pontoConsumoId")Long pontoConsumoId);

  @Query("""
            SELECT contratoPonto
            FROM
                ContratoPontoConsumo contratoPonto               
                inner join fetch contratoPonto.contrato
                inner join fetch contratoPonto.periodicidade
                inner join fetch contratoPonto.contrato.clienteAssinatura
                inner join fetch contratoPonto.pontoConsumo
                inner join fetch contratoPonto.pontoConsumo.imovel
                left join fetch contratoPonto.unidadePressao
            WHERE 1=1
            AND (contratoPonto.pontoConsumo.id = :pontoConsumoId)
            and (contratoPonto.contrato.contratoSituacao = :situacaoId)
            and (contratoPonto.contrato.habilitado = true)
            and (contratoPonto.contrato.chavePrimariaPrincipal is null)
            order by contratoPonto.id desc
            """)
  List<ContratoPontoConsumo> consultarContratoPontoConsumoPorPontoConsumoRecente(
           @Param("pontoConsumoId")Long pontoConsumoId,
           @Param("situacaoId")Long situacaoId
   );
}