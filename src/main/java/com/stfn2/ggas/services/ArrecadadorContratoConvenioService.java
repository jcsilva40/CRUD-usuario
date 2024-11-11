package com.stfn2.ggas.services;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoConvenioDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorContratoConvenioBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoConvenioFilterDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import com.stfn2.ggas.repositories.ArrecadadorContratoConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrecadadorContratoConvenioService extends BaseService<ArrecadadorContratoConvenio, ArrecadadorContratoConvenioDTO, ArrecadadorContratoConvenioBasicDTO, ArrecadadorContratoConvenioFilterDTO, ArrecadadorContratoConvenioRepository> {
    public List<ComboDTO> findArrecadadorPorCliente(Long tipoConvenioId, Long clienteId, Long pontoConsumoId){
        return this.repository.findAllArrecadadorPorCliente(TipoConvenioBancarioEnum.toEnum(tipoConvenioId), clienteId, pontoConsumoId);
    }

    public Boolean obterArrecadadorContratoConvenioPorClienteBancoCil(Long clienteId, Long bancoId, String cil, TipoConvenioBancarioEnum tipoConvenio){
        return this.repository.obterArrecadadorContratoConvenioPorClienteBancoCil(clienteId, bancoId, cil, tipoConvenio);
    }

    public ArrecadadorContratoConvenio obterArrecadadorContratoConvenioPadrao() {
        ArrecadadorContratoConvenio arrecadadorContratoConvenio = new ArrecadadorContratoConvenio();
        arrecadadorContratoConvenio = repository.obterArrecadadorContratoConvenioPadrao();

        return arrecadadorContratoConvenio;
    }

    public ArrecadadorContratoConvenio obterArrecadadorContratoConvenioPorDocumentoCobranca(DocumentoCobranca documentoCobranca){
        ArrecadadorContratoConvenio arrecadadorContratoConvenio = new ArrecadadorContratoConvenio();
        ContratoSituacaoEnum situacao = ContratoSituacaoEnum.ATIVO;

        arrecadadorContratoConvenio =
                repository.obterArrecadadorContratoConvenioPorDocumentoCobranca(documentoCobranca.getId(), situacao);


        return arrecadadorContratoConvenio;
    }
}