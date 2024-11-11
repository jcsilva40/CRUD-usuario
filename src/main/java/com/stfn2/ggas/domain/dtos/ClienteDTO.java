package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends BaseDTO {
    private Long id;

    private String nome;

    private String nomeFantasia;

    private String nomeAbreviado;

    private Short regimeRecolhimento;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;

    private String cpfCnpj;

    private String rg;

    private Long sexoId;

    private Long nacionalidadeId;

    private Long tipoCliente;

    private String emailsSegundariosEnvioFatura;

    private String nomeMae;

    private String nomeConjuge;

    private Long ufEmissaoRGId;

    private Long orgaoEmissorRGId;

    private String ieFaturamento;

    private LocalDate dataNascimento;

    private Long clienteSituacao;

    private List<ClienteContatoDTO> contatos = new ArrayList<>();

    private List<ClienteEnderecoDTO> enderecos = new ArrayList<>();

    public ClienteDTO(Cliente cliente) {
        super();
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
