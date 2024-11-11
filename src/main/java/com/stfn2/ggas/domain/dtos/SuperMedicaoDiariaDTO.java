package com.stfn2.ggas.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperMedicaoDiariaDTO extends BaseDTO {

    private Long id;
    private Integer versao;
    private Boolean habilitado;
    private Long anoMes;
    private Integer numeroCiclo;
    private String cil;
    private String dataRealizacaoLeitura;
    private BigDecimal leituraSemCorrecaoFatorPTZ;
    private BigDecimal leituraComCorrecaoFatorPTZ;
    private BigDecimal consumoSemCorrecaoFatorPTZ;
    private BigDecimal consumoComCorrecaoFatorPTZ;
    private BigDecimal pressao;
    private BigDecimal temperatura;
    private BigDecimal fatorPTZ;
    private BigDecimal fatorZ;
    private Boolean indicadorIntegrado;
    private Boolean indicadorProcessado;
    private LocalDateTime dataRegistroLeitura;
    private Boolean indicadorConsolidada;
    private Boolean indicadorMedidor;
    private Long supervisorioMedicaoAnormalidadeId;
    private Long ultimoUsuarioAlteracaoId;
    private Long statusId;
}
