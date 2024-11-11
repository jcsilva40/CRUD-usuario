package com.stfn2.ggas.domain.enumTypes;

public enum EnumTipoCliente {

    CPF(30L, "Pessoa Física"),
    CNPJ(31L, "Pessoa Juridica");

    private Long id;
    private String descricao;

    private EnumTipoCliente(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return this.id;
    }


}
