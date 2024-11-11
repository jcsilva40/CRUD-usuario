package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.FormaCobrancaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FormaCobrancaConverter implements AttributeConverter<FormaCobrancaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(FormaCobrancaEnum formaCobranca) {
        if (formaCobranca == null) {
            return null;
        }
        return formaCobranca.getId();
    }

    @Override
    public FormaCobrancaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return FormaCobrancaEnum.toEnum(id);
    }
}