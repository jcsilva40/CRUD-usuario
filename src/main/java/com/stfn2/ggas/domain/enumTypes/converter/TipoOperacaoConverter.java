package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoOperacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoOperacaoConverter implements AttributeConverter<TipoOperacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoOperacaoEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public TipoOperacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoOperacaoEnum.toEnum(id);
    }
}