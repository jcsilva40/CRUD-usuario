package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Medidor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private Long medidorMarcaId;
   private String medidorMarcaDescricao;
   private Long medidorModeloId;
   private String medidorModeloDescricao;
   private Long medidorCertificadoId;
   private LocalDateTime dataAquisicao;
   private BigDecimal pressaoMedicao;

   private String numeroInicial;
   private Integer quantidadeLote;
   private String prefixo;
   private String sufixo;

   public MedidorDTO(Medidor entity) {
      this.id = entity.getId();
      this.descricao = entity.getDescricao();
      this.medidorMarcaId = entity.getMedidorMarca().getId();
      this.medidorMarcaDescricao = entity.getMedidorMarca().getDescricao();
      this.medidorModeloId = entity.getMedidorModelo().getId();
      this.medidorModeloDescricao = entity.getMedidorModelo().getDescricao();
      this.dataAquisicao = entity.getDataAquisicao();
      this.pressaoMedicao = entity.getPressaoAtual();
   }

}