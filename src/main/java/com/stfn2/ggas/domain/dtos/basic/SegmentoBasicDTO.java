package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ContaContabil;
import com.stfn2.ggas.domain.Periodicidade;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.dtos.RamoAtividadeDTO;
import com.stfn2.ggas.domain.dtos.RamoAtividadeSubstituicaoTributariaDTO;
import com.stfn2.ggas.domain.enumTypes.SegmentoTipoEnum;
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
public class SegmentoBasicDTO extends BaseDTO {
   private Long id;
   private ContaContabil contaContabil;
   private Periodicidade periodicidade;
   private SegmentoTipoEnum segmentoTipo;
   private String descricao;
   private String abreviado;
   private String descricaoFatura;
   private Long fatorMultiplicacaoMediaAlto;
   private Long fatorMultplicacaoMediaEstouro;
   private Long fatorMultiplicacaoMediaVirada;
   private Long tipoPotencia;
   private Boolean programacaoConsumo;
   private Long numeroMediaCiclos;
   private Long numeroDiasParaFaturamento;
   private Long percentualMediaConsumo;
   private Long tamanhoReducao;
   private Long tipoVisualizacaoFatura;
   private Long volumeConsumoAlto;
   private Long volumeConsumoBaixo;
   private Long volumeEstouroConsumo;
   private Long volumeViradaMedidor;
   private Long volumeMedioEstouro;
   private String nomeSefaz;
   private Boolean habilitado = true;
   private List<RamoAtividadeDTO> ramosAtividade = new ArrayList<>();
   private List<RamoAtividadeSubstituicaoTributariaDTO> ramoAtividadeSubsTrib = new ArrayList<>();


   public SegmentoBasicDTO(Segmento entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}