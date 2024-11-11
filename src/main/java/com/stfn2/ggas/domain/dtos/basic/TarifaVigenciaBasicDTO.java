package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;
import com.stfn2.ggas.domain.enumTypes.BaseApuracaoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusAutorizacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCalculoEnum;
import com.stfn2.ggas.domain.enumTypes.UnidadeMonetariaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVigenciaBasicDTO extends BaseDTO {
   private Long id;
   private Long tarifaId;

   private BaseApuracaoEnum baseApuracao;
   private UnidadeMonetariaEnum unidadeMonetaria;
   private StatusAutorizacaoEnum status;
   private TipoCalculoEnum tipoCalculo;
   //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
   private LocalDate dataVigencia;

   private Long rubricaTermoFixoId;
   private BigDecimal valorTermoFixo = BigDecimal.ZERO;
   private Boolean usarPGU;
   private Boolean desconsiderarMargem;
   private Boolean msgICMSSubs;
   private BigDecimal valorMolecula = BigDecimal.ZERO;
   private BigDecimal valorTransporte = BigDecimal.ZERO;
   private BigDecimal valorRecuperacao = BigDecimal.ZERO;

   private List<TarifaVigenciaTributo> tributos = new ArrayList<>();
   private List<TarifaVigenciaFaixa> faixasConsumo = new ArrayList<>();
   private String mensagemICMSSubs;
   private String comentario;

	/*private Boolean msgICMSSubs;
	private BigDecimal valorBaseCalculoSubst = BigDecimal.ZERO;
	private BigDecimal valorIcmsSubs = BigDecimal.ZERO;*/

   private Long usuarioId;
   private Boolean habilitado;

   public TarifaVigenciaBasicDTO(TarifaVigencia entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}