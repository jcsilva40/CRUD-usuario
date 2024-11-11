package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoInfoCadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoInfoCadeConverter implements AttributeConverter<TipoInfoCadeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoInfoCadeEnum tipoInfoCade) {
        if (tipoInfoCade == null) {
            return null;
        }
        return tipoInfoCade.getId();
    }

    @Override
    public TipoInfoCadeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoInfoCadeEnum.toEnum(id);
    }
}