package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContratoSituacaoConverter implements AttributeConverter<ContratoSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ContratoSituacaoEnum contratoSituacao) {
        if (contratoSituacao == null) {
            return null;
        }
        return contratoSituacao.getId();
    }

    @Override
    public ContratoSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ContratoSituacaoEnum.toEnum(id);
    }
}