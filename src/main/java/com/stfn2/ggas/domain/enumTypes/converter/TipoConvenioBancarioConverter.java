package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoConvenioBancarioConverter implements AttributeConverter<TipoConvenioBancarioEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoConvenioBancarioEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoConvenioBancarioEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoConvenioBancarioEnum.toEnum(id);
    }
}
