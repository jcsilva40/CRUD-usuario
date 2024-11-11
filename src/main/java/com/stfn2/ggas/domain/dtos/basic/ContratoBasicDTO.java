package com.stfn2.ggas.domain.dtos.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContratoBasicDTO extends BaseDTO{
    
    private Long id;
    
    //private String descricaoContrato;
    
    private Long clienteId;
    
    private String nomeCliente;
    
    private String cpfCnpjCliente;
    
    private Integer numeroContrato;
    
    //@JsonSerialize(using = ContratoSituacaoSerializer.class)
    private ContratoSituacaoEnum contratoSituacao;
    
    private Long pontoConsumoId;
    
    private String descricaoPontoConsumo;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAssinatura;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    
}       