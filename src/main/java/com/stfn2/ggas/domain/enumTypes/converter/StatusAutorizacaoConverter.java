package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.StatusAutorizacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusAutorizacaoConverter implements AttributeConverter<StatusAutorizacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusAutorizacaoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public StatusAutorizacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return StatusAutorizacaoEnum.toEnum(id);
    }
}
