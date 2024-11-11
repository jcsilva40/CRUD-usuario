package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MostradorTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MostradorTipoConverter implements AttributeConverter<MostradorTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MostradorTipoEnum mostradorTipoEnum) {
        if (mostradorTipoEnum == null) {
            return null;
        }
        return mostradorTipoEnum.getId();
    }

    @Override
    public MostradorTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MostradorTipoEnum.toEnum(id);
    }
}
