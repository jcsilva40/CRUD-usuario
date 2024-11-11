package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoDescontoCadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDescontoCadeConverter implements AttributeConverter<TipoDescontoCadeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoDescontoCadeEnum tipoDescontoCade) {
        if (tipoDescontoCade == null) {
            return null;
        }
        return tipoDescontoCade.getId();
    }

    @Override
    public TipoDescontoCadeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoDescontoCadeEnum.toEnum(id);
    }
}