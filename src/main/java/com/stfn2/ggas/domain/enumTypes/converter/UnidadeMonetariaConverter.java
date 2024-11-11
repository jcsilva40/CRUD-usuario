package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.UnidadeMonetariaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UnidadeMonetariaConverter implements AttributeConverter<UnidadeMonetariaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(UnidadeMonetariaEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public UnidadeMonetariaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return UnidadeMonetariaEnum.toEnum(id);
    }
}
