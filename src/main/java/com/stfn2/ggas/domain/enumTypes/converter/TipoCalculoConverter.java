package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoCalculoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCalculoConverter implements AttributeConverter<TipoCalculoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoCalculoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoCalculoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoCalculoEnum.toEnum(id);
    }
}
