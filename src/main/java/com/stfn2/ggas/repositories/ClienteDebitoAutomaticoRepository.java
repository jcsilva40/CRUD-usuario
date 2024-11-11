package com.stfn2.ggas.repositories;

import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import com.stfn2.ggas.domain.dtos.filter.ClienteDebitoAutomaticoFilterDTO;

@Repository
public interface ClienteDebitoAutomaticoRepository extends BaseRepository<ClienteDebitoAutomatico, ClienteDebitoAutomaticoFilterDTO> {

   @Query("""
        SELECT 
            clienteDebitoAutomatico
        FROM ClienteDebitoAutomatico clienteDebitoAutomatico
             left join fetch clienteDebitoAutomatico.cliente
        WHERE 1=1
           AND (clienteDebitoAutomatico.cliente.id = :clienteId)
           AND (clienteDebitoAutomatico.contrato.id = :contratoPaiId)
        
        """)
   ClienteDebitoAutomatico obterClienteDebitoAutomatico(@Param("clienteId")Long clienteId,
                                                        @Param("contratoPaiId") Long contratoPaiId);
   
}