package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.dto.UserDTO;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuperMedicaoHorariaDTO extends BaseDTO {
    private Long id;
    private Integer versao;
    private LocalDateTime ultimaAlteracao;
    private Boolean habilitado;
    private Long codigoPontoConsumoSuper;
    private String indicadorTipoMedicao;
    private LocalDateTime dataRealizacaoLeitura;
    private Long leituraSemCorrecaoFatorPTZ;
    private Long leituraComCorrecaoFatorPTZ;
    private Long pressao;
    private Long temperatura;
    private Boolean indicadorConsolidada;
    private LocalDateTime dataRegistroLeitura;
    private String indicadorTipoInclusaoMedicao;
    private SuperMedicaoDiariaDTO supervisorioMedicaoDiaria;
    private UserDTO ultimoUsuarioAlteracao;
    private EntidadeConteudoDTO status;
}
