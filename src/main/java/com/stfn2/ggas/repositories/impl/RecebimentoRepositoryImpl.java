package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Recebimento;
import com.stfn2.ggas.domain.dtos.filter.RecebimentoFilterDTO;
import jakarta.persistence.EntityManager;

public class RecebimentoRepositoryImpl extends IRepository<Recebimento, RecebimentoFilterDTO> {

    public RecebimentoRepositoryImpl(EntityManager em){
        super(em, "id");
    }

    @Override
    protected void filters(RecebimentoFilterDTO filter, ExecucaoQuery<Recebimento> execute){

        addFilterPositive(execute, "anoMesContabil", filter.getAnoMesContabil());
        addFilterSubAttribute2(execute, "faturaGeral", "faturaAtual", "ciclo", filter.getFaturaGeralFaturaCiclo());
        addFilterSubAttribute2(execute, "pontoConsumo", "segmento", "descricao", filter.getPontoConsumoSegmentoDescricao());
        addFilterSubAttribute3(execute, "faturaGeral", "faturaAtual", "situacaoPagamento", "descricao", filter.getFaturaGeralFaturaSituacaoPagamento());
        addFilterSubAttribute2(execute, "faturaGeral", "faturaAtual", "numeroFatura", filter.getFaturaGeralFaturaNumeroFatura());


    }
}
