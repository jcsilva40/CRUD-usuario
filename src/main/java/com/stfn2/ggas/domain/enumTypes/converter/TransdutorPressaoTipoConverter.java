package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TransdutorPressaoTipoEnum;
import jakarta.persistence.AttributeConverter;

public class TransdutorPressaoTipoConverter implements AttributeConverter<TransdutorPressaoTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TransdutorPressaoTipoEnum transdutorPressaoTipoEnum) {
        if (transdutorPressaoTipoEnum == null) {
            return null;
        }
        return transdutorPressaoTipoEnum.getId();
    }

    @Override
    public TransdutorPressaoTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TransdutorPressaoTipoEnum.toEnum(id);
    }
}
