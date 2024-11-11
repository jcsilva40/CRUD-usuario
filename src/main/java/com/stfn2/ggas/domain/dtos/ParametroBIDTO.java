package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ParametroBI;
import com.stfn2.ggas.domain.ValorDefinidoFixoBI;
import com.stfn2.ggas.domain.enumTypes.TipoEntradaBIEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPrimitivoParametroBIEnum;
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
public class ParametroBIDTO extends BaseDTO {

    private String descricao;

    private String key;

    private String valorPadrao;

    private Boolean obrigatorio;

    private String entidadePesquisa;

    private TipoEntradaBIEnum tipoEntrada;

    private TipoPrimitivoParametroBIEnum tipoPrimitivo;
    
    private Short quantidadeMeses;

    private Integer posicao;

    private List<ValorDefinidoFixoBIDTO> valoresFixos = new ArrayList<>();

    public ParametroBIDTO(ParametroBI pOb) {
        super();
        setId(pOb.getId());
        setDescricao(pOb.getDescricao());
        setKey(pOb.getKey());
        setValorPadrao(pOb.getValorPadrao());
        setObrigatorio(pOb.getObrigatorio());
        setEntidadePesquisa(pOb.getEntidadePesquisa());
        setTipoEntrada(pOb.getTipoEntrada());
        setTipoPrimitivo(pOb.getTipoPrimitivo());
        setQuantidadeMeses(pOb.getQuantidadeMeses());
        setPosicao(pOb.getPosicao());
        pOb.getValoresFixos().forEach(i-> getValoresFixos().add(new ValorDefinidoFixoBIDTO(i)));
    }

}