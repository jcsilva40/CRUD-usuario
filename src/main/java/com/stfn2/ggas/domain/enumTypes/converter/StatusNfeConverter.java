package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.StatusNfeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusNfeConverter implements AttributeConverter<StatusNfeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusNfeEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public StatusNfeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return StatusNfeEnum.toEnum(id);
    }
}