package com.stfn2.ggas.domain.enumTypes;
import lombok.Getter;

@Getter
public enum LeituraSituacaoEnum {

    NAO_REALIZADA(1L, "Não Realizada"),
    REALIZADA(2L, "Realizada"),
    CONFIRMADA(3L, "Confirmada");

    private  Long id;
    private  String descricao;

     LeituraSituacaoEnum(Long id, String descricao){
         this.id = id;
         this.descricao = descricao;
    }

    public static LeituraSituacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (LeituraSituacaoEnum x : LeituraSituacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
