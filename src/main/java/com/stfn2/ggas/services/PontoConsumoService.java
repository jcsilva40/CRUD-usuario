package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;
import com.stfn2.ggas.domain.dtos.EnderecoDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum;
import com.stfn2.ggas.repositories.PontoConsumoRepository;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.PontoConsumoRotaVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PontoConsumoService extends BaseService<PontoConsumo, PontoConsumoDTO, PontoConsumoBasicDTO, PontoConsumoFilterDTO, PontoConsumoRepository> {

    public EnderecoDTO findEnderecoByPontoConsumo(Long idPontoConsumo) {
        return repository.findEnderecoByPontoConsumo(idPontoConsumo);        
    }
    
    public List<ComboDTO> findPontoConsumoSemContratoAtivo(){
        return this.repository.findPontoConsumoSemContratoAtivo();
    }
    
    public Boolean pontoConsumoComContratoSemLiberacaoGas(Long pontoConsumoId){
        return this.repository.pontoConsumoComContratoSemLiberacaoGas(pontoConsumoId);
    }
    
    public Long pontoConsumoComContratoId(Long pontoConsumoId){
        return this.repository.pontoConsumoComContratoId(pontoConsumoId);
    }
    
    public ContratoStatusEnum pontoConsumoContratoStatus(Long pontoConsumoId){
        return this.repository.pontoConsumoContratoStatus(pontoConsumoId);
    }

    public List<PontoConsumoTributoAliquota> obterListaPontoConsumoTributoAliquota(Long pontoConsumoId){
        return this.repository.obterListaPontoConsumoTributoAliquota(pontoConsumoId);
    }

    public List<PontoConsumoRotaVO> listaPontoConsumoRota( Long rotaId) {
        return this.repository.listaPontoConsumoRota(rotaId);
    }
    
    public LocalDate consultarExistenciaDeDataAssinaturaNoImovel(Long pontoConsumoId){
        return repository.consultarExistenciaDeDataAssinaturaNoImovel(pontoConsumoId);
    }

    public List<PontoConsumo> obterListaPontoConsumoPorImovel(Long imovelId) {
        return repository.obterListaPontoConsumoPorImovel(imovelId);
    }

    public boolean existsByCil(String cil) {
        return repository.existsByCil(cil);
    }
}

