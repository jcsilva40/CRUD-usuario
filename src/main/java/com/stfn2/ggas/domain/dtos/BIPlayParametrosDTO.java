package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ParametroBI;
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
public class BIPlayParametrosDTO extends BaseDTO {

    private Long id;

    private String descricao;

    private TipoEntradaBIEnum tipoEntrada;

    private String key;

    private String entidadePesquisa;

    private Integer posicao;
    
    private Short quantidadeMeses;

    private TipoPrimitivoParametroBIEnum tipoPrimitivo;

    private String valorPadrao;

    private List<ValorFixoPlayBIDTO> valoresFixos = new ArrayList<>();

    public BIPlayParametrosDTO(ParametroBI e) {
        super();
        setDescricao(e.getDescricao());
        setKey(e.getKey());
        setTipoEntrada(e.getTipoEntrada());
        setEntidadePesquisa(e.getEntidadePesquisa());
        setPosicao(e.getPosicao());
        setTipoPrimitivo(e.getTipoPrimitivo());
        setValorPadrao(e.getValorPadrao());
        setQuantidadeMeses(e.getQuantidadeMeses());
        e.getValoresFixos().forEach(i-> getValoresFixos().add(new ValorFixoPlayBIDTO(i)));
    }

    @Override
    public Long getId() {
        return this.id;
    }
}