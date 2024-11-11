package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.CorretorMarcaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CorretorMarcaConverter implements AttributeConverter<CorretorMarcaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(CorretorMarcaEnum corretorMarcaEnum) {
        if (corretorMarcaEnum == null) {
            return null;
        }
        return corretorMarcaEnum.getId();
    }

    @Override
    public CorretorMarcaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return CorretorMarcaEnum.toEnum(id);
    }
}
