package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum;
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
public class PontoConsumoDTO extends BaseDTO {

   private Long id;
   private String descricao;
   private String complementoCil;
   private String cil;
   //private Cep cep;
   private Long segmentoId;
   private Integer sequenciaLeitura;
   private Long ramoAtividadeId;
   private String segmentoDescricao;
   private String observacao;
   private Long pontoConsumoSituacaoId;
   private String pontoConsumoSituacaoDescricao;
   private Long modalidadeUsoId;
   private Long rotaId;
   private String rotaDescricao;
   private String codigoLegado;
   private Long medidorInstalacaoId;
   private String medidorInstalacaoMedidorDescricao;
   private LocalDate medidorInstalacaoDataAtivacao;
   private LocalDate medidorInstalacaoData;
   @JsonProperty("pressaoAtual")
   private BigDecimal medidorInstalacaoMedidorPressaoAtual;
   private Boolean habilitado = true;
   private String mensagemFatura;
   private Integer fatorCorrecao;
   private Boolean enderecoEntregaIgualLocal;
   private Long cepId;
   private String logradouro;
   private String cep;
   private String bairro;
   private String localidade;
   private String uf;
   private String numero;
   private String complemento;
   private Long contratoId;
   private ContratoStatusEnum contratoStatus;
   List<PontoConsumoMedidorInstalacaoDTO> listaInstalacoes = new ArrayList<>();


    @Override
    public Long getId() {
        return this.id;
    }
}
