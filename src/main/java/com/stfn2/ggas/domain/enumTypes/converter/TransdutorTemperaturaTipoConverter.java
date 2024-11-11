package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TransdutorTemperaturaTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransdutorTemperaturaTipoConverter implements AttributeConverter<TransdutorTemperaturaTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TransdutorTemperaturaTipoEnum transdutorTemperaturaTipoEnum) {
        if (transdutorTemperaturaTipoEnum == null) {
            return null;
        }
        return transdutorTemperaturaTipoEnum.getId();
    }

    @Override
    public TransdutorTemperaturaTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TransdutorTemperaturaTipoEnum.toEnum(id);
    }
}
