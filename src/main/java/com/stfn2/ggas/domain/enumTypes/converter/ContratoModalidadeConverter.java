package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ContratoModalidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContratoModalidadeConverter implements AttributeConverter<ContratoModalidadeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ContratoModalidadeEnum contratoModalidade) {
        if (contratoModalidade == null) {
            return null;
        }
        return contratoModalidade.getId();
    }

    @Override
    public ContratoModalidadeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ContratoModalidadeEnum.toEnum(id);
    }
}