package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoFaturamentoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoFaturamentoConverter implements AttributeConverter<TipoFaturamentoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoFaturamentoEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public TipoFaturamentoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoFaturamentoEnum.toEnum(id);
    }
}