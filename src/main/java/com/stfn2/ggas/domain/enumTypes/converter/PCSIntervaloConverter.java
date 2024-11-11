package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.PCSIntervaloEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PCSIntervaloConverter implements AttributeConverter<PCSIntervaloEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(PCSIntervaloEnum intervaloPCS) {
        if (intervaloPCS == null) {
            return null;
        }
        return intervaloPCS.getId();
    }

    @Override
    public PCSIntervaloEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return PCSIntervaloEnum.toEnum(id);
    }
}