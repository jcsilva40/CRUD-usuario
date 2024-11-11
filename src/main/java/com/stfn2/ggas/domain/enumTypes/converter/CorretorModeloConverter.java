package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.CorretorModeloEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CorretorModeloConverter implements AttributeConverter<CorretorModeloEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(CorretorModeloEnum corretorModeloEnum) {
        if (corretorModeloEnum == null) {
            return null;
        }
        return corretorModeloEnum.getId();
    }

    @Override
    public CorretorModeloEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return CorretorModeloEnum.toEnum(id);
    }
}
