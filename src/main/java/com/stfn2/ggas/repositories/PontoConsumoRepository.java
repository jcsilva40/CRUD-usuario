package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;
import com.stfn2.ggas.domain.dtos.EnderecoDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.PontoConsumoRotaVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PontoConsumoRepository extends BaseRepository<PontoConsumo, PontoConsumoFilterDTO> {
    @Query("""
            SELECT
                pocn.segmento.id
            FROM
                PontoConsumo pocn
            WHERE 1=1
                AND(pocn.id = :idPontoConsumo)
            """)
    Long findIdSegmentoPorPontoConsumo(@Param("idPontoConsumo") Long idPontoConsumo);
    
    @Query("""
            SELECT 
            new com.stfn2.ggas.domain.dtos.EnderecoDTO(
                pocn.cepTabela.id as cepId,
                pocn.logradouro as logradouro,
                pocn.cep as cep,
                pocn.bairro as bairro,
                pocn.localidade as localidade,
                pocn.uf as uf,
                pocn.numero as numero,
                pocn.complemento as complemento
            )
            FROM
                PontoConsumo pocn
            WHERE 1=1
                AND(pocn.id = :idPontoConsumo)
            """)
    EnderecoDTO findEnderecoByPontoConsumo(@Param("idPontoConsumo") Long idPontoConsumo);
    
    @Query("""
            SELECT 
            new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                pocn.id,
                pocn.descricao
            )
            FROM
                PontoConsumo pocn
            WHERE 1=1
                AND pocn NOT IN (
                        SELECT copc.pontoConsumo
                        FROM
                            Contrato cont
                        JOIN 
                            cont.contratoPontoConsumo copc
                        WHERE
                            cont.contratoSituacao IN (ContratoSituacaoEnum.ATIVO, ContratoSituacaoEnum.ALTERADO)
                            AND cont.dataLiberacaoGas IS NOT NULL
                        )
                
            """)
    List<ComboDTO> findPontoConsumoSemContratoAtivo();
    
    @Query("""
            SELECT 
                CASE WHEN COUNT(cont) > 0 THEN true ELSE false END
            FROM
                Contrato cont
            JOIN 
                cont.contratoPontoConsumo copc
            WHERE
                copc.pontoConsumo.id = :pontoConsumoId
                AND cont.contratoSituacao IN (ContratoSituacaoEnum.ATIVO, ContratoSituacaoEnum.ALTERADO)
                AND cont.dataLiberacaoGas IS NULL
            """)
    Boolean pontoConsumoComContratoSemLiberacaoGas(@Param("pontoConsumoId") Long pontoConsumoId);
    
    @Query("""
            SELECT 
                CASE WHEN COUNT(cont) > 0 THEN MIN(cont.id) ELSE null END
            FROM
                Contrato cont
            JOIN 
                cont.contratoPontoConsumo copc
            WHERE
                copc.pontoConsumo.id = :pontoConsumoId
                AND cont.contratoSituacao IN (ContratoSituacaoEnum.ATIVO, ContratoSituacaoEnum.ALTERADO)        
            """)
    Long pontoConsumoComContratoId(@Param("pontoConsumoId") Long pontoConsumoId);
    
    @Query("""
            SELECT 
                CASE 
                    WHEN COUNT(cont) > 0 AND cont.dataLiberacaoGas IS NOT NULL THEN com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum.ATIVO_COM_GAS
                    WHEN COUNT(cont) > 0 AND cont.dataLiberacaoGas IS NULL THEN com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum.PENDENTE_LIBERACAO_GAS
                    ELSE com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum.INEXISTENTE
                END
            FROM
                Contrato cont
            JOIN 
                cont.contratoPontoConsumo copc
            WHERE
                copc.pontoConsumo.id = :pontoConsumoId
                AND cont.contratoSituacao IN (ContratoSituacaoEnum.ATIVO, ContratoSituacaoEnum.ALTERADO)
            GROUP BY
                cont.id, cont.dataLiberacaoGas
            """)
    ContratoStatusEnum pontoConsumoContratoStatus(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
            SELECT 
               tributoAliquota
            FROM
                PontoConsumoTributoAliquota tributoAliquota
            
            WHERE
                tributoAliquota.pontoConsumo.id = :pontoConsumoId
            """)
    List<PontoConsumoTributoAliquota> obterListaPontoConsumoTributoAliquota(@Param("pontoConsumoId") Long pontoConsumoId);
    
    @Query("""
            SELECT 
                MIN(cont.dataAssinatura)
            FROM
                PontoConsumo pocn
            INNER JOIN 
                pocn.imovel imov               
            INNER JOIN
                imov.pontoConsumos listaPocn
            INNER JOIN
                listaPocn.listaContratoPontoConsumo listaCopc
            INNER JOIN
                listaCopc.contrato cont
            WHERE  
                pocn.id = :pontoConsumoId                       
            """)
    LocalDate consultarExistenciaDeDataAssinaturaNoImovel(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
            SELECT
                p
            FROM
                PontoConsumo p
            WHERE
                p.imovel.id = :imovelId
            """)
    List<PontoConsumo> obterListaPontoConsumoPorImovel(@Param("imovelId") Long imovelId);

    @Query("""
        SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
        FROM PontoConsumo p
        WHERE p.cil = :cil
    """)
    boolean existsByCil(String cil);

    //List<PontoConsumo> obterListaPontoConsumoPorImovel(Long imovelId);

    @Query("""
            SELECT new com.stfn2.ggas.services.componentes.faturamentogruporota.vo.PontoConsumoRotaVO 
                (
                pocn.id,
                pocn.descricao,
                pocn.cil
                )
            FROM
                PontoConsumo pocn
            WHERE
                pocn.rota.id = :rotaId
            """)
    List<PontoConsumoRotaVO> listaPontoConsumoRota(@Param("rotaId") Long rotaId);

    @Query("SELECT ci.cliente FROM PontoConsumo pc " +
            "JOIN pc.imovel i " +
            "JOIN i.clienteImovel ci " +
            "WHERE pc.id = :pontoConsumoId")
    Optional<Cliente> findClienteByPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
        SELECT s.periodicidade.quantidadeDias
        FROM PontoConsumo p
        JOIN p.segmento s
        WHERE p.id = :idPontoConsumo
        """)
    Integer obterQuantidadeDiasFaturamentoByPontoConsumo(@Param("idPontoConsumo") Long idPontoConsumo);
}
