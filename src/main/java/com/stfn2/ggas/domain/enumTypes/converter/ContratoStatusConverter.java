package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ContratoStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContratoStatusConverter implements AttributeConverter<ContratoStatusEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ContratoStatusEnum contratoStatus) {
        if (contratoStatus == null) {
            return null;
        }
        return contratoStatus.getId();
    }

    @Override
    public ContratoStatusEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ContratoStatusEnum.toEnum(id);
    }
}