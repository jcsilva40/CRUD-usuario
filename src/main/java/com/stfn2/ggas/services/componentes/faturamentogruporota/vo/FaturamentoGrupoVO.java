package com.stfn2.ggas.services.componentes.faturamentogruporota.vo;

import com.stfn2.ggas.domain.FaturamentoGrupo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FaturamentoGrupoVO {
    private Long id;
    private String descricao;
    private Integer anoMes;
    private Integer ciclo;
    private List<RotaFaturamentoGrupoVO> rotas;

    public FaturamentoGrupoVO(Long id, String descricao, Integer anoMes, Integer ciclo) {
        this.id = id;
        this.descricao = descricao;
        this.anoMes = anoMes;
        this.ciclo = ciclo;
    }
}
