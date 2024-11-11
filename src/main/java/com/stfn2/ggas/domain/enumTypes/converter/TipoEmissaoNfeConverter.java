package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoEmissaoNfeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEmissaoNfeConverter implements AttributeConverter<TipoEmissaoNfeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoEmissaoNfeEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public TipoEmissaoNfeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoEmissaoNfeEnum.toEnum(id);
    }
}