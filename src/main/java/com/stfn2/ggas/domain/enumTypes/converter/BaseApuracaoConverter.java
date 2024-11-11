package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.BaseApuracaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BaseApuracaoConverter implements AttributeConverter<BaseApuracaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(BaseApuracaoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public BaseApuracaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return BaseApuracaoEnum.toEnum(id);
    }
}
