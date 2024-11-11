package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.dtos.filter.ClienteFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, ClienteFilterDTO> {
    @Query("""
            SELECT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                    clie.id as id,
                    CASE 
                        WHEN clie.cpf IS NOT NULL THEN 
                            CONCAT(clie.nome, ' (CPF: ', SUBSTRING(clie.cpf, 1, 3),
                            '.', SUBSTRING(clie.cpf, 4, 3), '.', SUBSTRING(clie.cpf, 7, 3), '-',
                            SUBSTRING(clie.cpf, 10, 2), ')')
                        WHEN clie.cnpj IS NOT NULL THEN 
                            CONCAT(clie.nome, ' (CNPJ: ', SUBSTRING(clie.cnpj, 1, 2), '.',
                            SUBSTRING(clie.cnpj, 3, 3), '.', SUBSTRING(clie.cnpj, 6, 3), '/',
                            SUBSTRING(clie.cnpj, 9, 4), '-', SUBSTRING(clie.cnpj, 13, 2), ')')                       
                    END
                )
            FROM
                Cliente clie            
            ORDER BY
                clie.id DESC
            """)
    List<ComboDTO> findAllCliente();
}
