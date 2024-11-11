package com.stfn2.ggas.services.componentes.faturamentogruporota.vo;

import com.stfn2.ggas.domain.Rota;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RotaFaturamentoGrupoVO {
    private Long id;
    private String descricao;
    private List<PontoConsumoRotaVO> pontosDeConsumo;

    public RotaFaturamentoGrupoVO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
