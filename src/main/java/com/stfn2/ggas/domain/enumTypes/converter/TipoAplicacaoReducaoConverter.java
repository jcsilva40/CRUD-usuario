package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoReducaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAplicacaoReducaoConverter implements AttributeConverter<TipoAplicacaoReducaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoAplicacaoReducaoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoAplicacaoReducaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoAplicacaoReducaoEnum.toEnum(id);
    }
}
