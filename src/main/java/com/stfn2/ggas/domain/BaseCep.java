package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BaseCepDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.stfn2.ggas.tools.ToolStr.aplicacarMascaraCep;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIA_CEP")
public class BaseCep extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VIA_CEP_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VIA_CEP")
    @SequenceGenerator(name = "SQ_VIA_CEP", sequenceName = "SQ_VIA_CEP_CD", allocationSize = 1)
    private Long id;

    
    public BaseCep(Long id) {
        super(id);
        this.id = id;
    }

    @Override
    public String getDescricao() {
        return "";
    }

    @Override
    public void setDescricao(String descricao) {

    }

    @Column(name = "IN_USO")
    private Boolean habilitado = true;

    @Column(name = "NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Override
    public BaseDTO convert() {
        BaseCepDTO cep = MapperImpl.parseObject(this, BaseCepDTO.class);
        cep.setCep(aplicacarMascaraCep(cep.getCep()));
        return cep;
    }

    private String localidade; // Nome da cidade correspondente ao CEP.
    private String uf; // Sigla do estado correspondente ao CEP.
    private String cep; //O CEP consultado.
    private String logradouro; //Nome da rua ou avenida correspondente ao CEP.
    private String complemento; // Informações adicionais sobre o endereço, como bloco, apartamento, lado ímpar, lado par, etc.
    private String bairro; // Nome do bairro correspondente ao CEP.
    private String gia; //Código GIA (Guia de Informação e Apuração) da cidade.
    private String ibge; //Código IBGE da cidade.
    private String ddd; //Código DDD (Discagem Direta a Distância) da cidade.
    private String siafi; // Código SIAFI (Sistema Integrado de Administração Financeira do Governo Federal) da cidade.

    public void setCep(String cep) {
        this.cep = cep.replaceAll("-","");
    }

}
