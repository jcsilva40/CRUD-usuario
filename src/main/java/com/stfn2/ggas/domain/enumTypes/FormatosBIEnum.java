package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormatosBIEnum {

    JSON(1L, "JSON", "JSON"),
    XLSX(2L, "XLSX", "XLSX"),    
    PDF(3L, "PDF", "PDF"),
    CSV(4L, "CSV", "CSV");
    

    private final Long id;
    private final String descricao;
    private final String abreviado;

    FormatosBIEnum(Long id, String descricao, String abreviado) {
        this.id = id;
        this.descricao = descricao;
        this.abreviado = abreviado;
    }

    public static FormatosBIEnum toEnum(Long id) {
        if (id == null) {
            return null;
        }

        for (FormatosBIEnum x : FormatosBIEnum.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

    @JsonValue
    public Long getId() {
        return id;
    }
    
    public String toLowerCase(){
        return descricao.toLowerCase();
    }
}
