package com.stfn2.ggas.domain.enumTypes;
import lombok.Getter;
@Getter
public enum MedidorSituacaoEnum {

    COM_DEFEITO(1L),
    INSTALADO(3L),
    EM_AFERICAO(6L),
    PRONTO_PARA_INSTALAR(7L),
    DESMOBILIZADO(8L);

    private final Long id;

     MedidorSituacaoEnum(Long id){
        this.id = id;
    }

    public static MedidorSituacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorSituacaoEnum x : MedidorSituacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
