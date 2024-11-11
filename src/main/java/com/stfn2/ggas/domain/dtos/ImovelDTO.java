package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Imovel;
import com.stfn2.ggas.domain.enumTypes.*;
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
public class ImovelDTO extends BaseDTO {

    private Long id;
    private String descricao;
    private Long imovelPaiId;
    private Integer nip;
    private String zonaBloqueio;
    private ImovelSituacaoEnum imovelSituacao;
    private ImovelOrigemEnum imovelOrigem;
    private ImovelTipoMedicaoEnum imovelTipoMedicao;
    private ImovelTipoMedicaoGLPEnum imovelTipoMedicaoGLP;
    private Integer numeroUdas;

    private Boolean condominio = false;
    private String numero;
    private String complementoEndereco;
    private Long rotaId;
    private Long segmentoId;
    private LocalDate dataEntrega;
    private LocalDate dataCriacao;
    private LocalDate dataTesteEstanque;
    private Boolean habilitado;
    private Integer quantidadePontosConsumo;


    private String cep;
    private String logradouro;
    private String bairro;
    @JsonProperty("localidade")
    private String municipio;
    private String uf;
    private Long contratoId;
    private ContratoStatusEnum situacaoContrato;


    private List<UnidadeConsumidoraDTO> unidadesConsumidoras = new ArrayList<>();

    private List<PontoConsumoDTO> pontoConsumos = new ArrayList<>();

    private List<ClienteImovelDTO> clienteImovel = new ArrayList<>();

    public ImovelDTO(Imovel entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.quantidadePontosConsumo = entity.getQuantidadePontosConsumo();
    }

    @Override
    public Long getId() {
        return this.id;
    }
}