package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ClienteContato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteContatoDTO extends BaseDTO {

    private String nome;

    private Integer codigoDDD;

    private Integer fone;

    private Integer ramal;

    private Integer codigoDDD2;

    private Integer fone2;

    private Integer ramal2;

    private String observacao;

    private String email;

    private String cpf;

    private String rg;

    private Boolean principal;

    private Long tipoContatoId;

    public ClienteContatoDTO(ClienteContato obj) {
        super();
    }

}

