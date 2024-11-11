package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoParametroSistemaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoParametroSistemaConverter implements AttributeConverter<TipoParametroSistemaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoParametroSistemaEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public TipoParametroSistemaEnum convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return TipoParametroSistemaEnum.toEnum(id);
    }
}