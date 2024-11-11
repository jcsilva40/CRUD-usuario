package com.stfn2.ggas.services.componentes.calculotarifa;

import com.stfn2.ggas.services.componentes.calculotarifa.vo.DadosFaixaVO;

import java.math.BigDecimal;
import java.util.List;

public interface TipoCalculoInterface {
   List<DadosFaixaVO> calcular(BigDecimal volume);
   Long tipoCalculo();
   String descricao();
}
