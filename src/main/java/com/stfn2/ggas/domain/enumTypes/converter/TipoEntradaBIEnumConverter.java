package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoEntradaBIEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEntradaBIEnumConverter implements AttributeConverter<TipoEntradaBIEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoEntradaBIEnum tipoEntradaBIEnum) {
        if (tipoEntradaBIEnum == null) {
            return null;
        }
        return tipoEntradaBIEnum.getId();
    }

    @Override
    public TipoEntradaBIEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoEntradaBIEnum.toEnum(id);
    }
}