package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorInstalacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorInstalacaoDTO extends BaseDTO {
        private Long id;
        private Long pontoConsumoId;
        private String pontoConsumoDescricao;
        private String pontoConsumoCil;
        private Integer pontoConsumoSequenciaLeitura;

        private Long medidorId;
        private String medidorDescricao;
        private String medidorModeloDescricao;
        private String medidorMarcaDescricao;

        private Long novoMedidorId;
        private Long corretorVazaoId;
        private LocalDate data;
        private LocalDate dataAtivacao;
        private LocalDate dataRetirada;
        //private LocalDate dataNovaInstalacao;
        private boolean retirada;
        private BigDecimal leituraInicial;
        private BigDecimal leituraAtivacao = this.leituraInicial;
        private BigDecimal leituraAnterior;
        private BigDecimal leituraFinal;
        private BigDecimal fatorCorrecao;
        private BigDecimal consumoAFaturar;

    	public MedidorInstalacaoDTO (MedidorInstalacao entity) {super(); 	}

}