package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.SegmentoTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SegmentoTipoConverter implements AttributeConverter<SegmentoTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(SegmentoTipoEnum segmentoTipo) {
        if (segmentoTipo == null) {
            return null;
        }
        return segmentoTipo.getId();
    }

    @Override
    public SegmentoTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return SegmentoTipoEnum.toEnum(id);
    }
}
