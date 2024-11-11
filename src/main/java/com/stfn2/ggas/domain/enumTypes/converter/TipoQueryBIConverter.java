package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoQueryBIEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoQueryBIConverter implements AttributeConverter<TipoQueryBIEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoQueryBIEnum tipoQueryBI) {
        if (tipoQueryBI == null) {
            return null;
        }
        return tipoQueryBI.getId();
    }

    @Override
    public TipoQueryBIEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoQueryBIEnum.toEnum(id);
    }
}