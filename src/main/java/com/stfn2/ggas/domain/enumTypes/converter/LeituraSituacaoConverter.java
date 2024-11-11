package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.LeituraSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LeituraSituacaoConverter implements AttributeConverter<LeituraSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(LeituraSituacaoEnum obj) {
        if (obj == null) {
            return null;
        }
        return obj.getId();
    }

    @Override
    public LeituraSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return LeituraSituacaoEnum.toEnum(id);
    }
}