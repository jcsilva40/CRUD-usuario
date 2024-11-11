package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ConsumoTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConsumoTipoConverter implements AttributeConverter<ConsumoTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ConsumoTipoEnum consumoTipo) {
        if (consumoTipo == null) {
            return null;
        }
        return consumoTipo.getId();
    }

    @Override
    public ConsumoTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ConsumoTipoEnum.toEnum(id);
    }
}