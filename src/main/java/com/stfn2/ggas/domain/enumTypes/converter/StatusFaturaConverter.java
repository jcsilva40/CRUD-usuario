package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusFaturaConverter implements AttributeConverter<StatusFaturaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusFaturaEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public StatusFaturaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return StatusFaturaEnum.toEnum(id);
    }
}
