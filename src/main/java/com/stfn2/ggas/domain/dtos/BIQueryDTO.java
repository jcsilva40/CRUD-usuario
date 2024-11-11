package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.BIQuery;
import com.stfn2.ggas.domain.enumTypes.TipoQueryBIEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BIQueryDTO extends BaseDTO {
    private String descricao;
    private Boolean principal;
    private String query;
    private TipoQueryBIEnum tipoQuery;
    private String nomeField;
    private String conteudoJasper;
    private List<BIQueryDTO> children = new ArrayList<>();

    public BIQueryDTO(BIQuery e) {
        super();
        setId(e.getId());
        setDescricao(e.getDescricao());
        setPrincipal(e.getPrincipal());
        setQuery(e.getQuery());
        setTipoQuery(e.getTipoQuery());
        setNomeField(e.getNomeField());
        e.getChildren().forEach(i -> getChildren().add(new BIQueryDTO(i)));

        if (e.getJasper() != null)
            setConteudoJasper(e.getJasper().getConteudo());

    }

}