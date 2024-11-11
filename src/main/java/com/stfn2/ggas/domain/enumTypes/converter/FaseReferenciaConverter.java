package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.FaseReferenciaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FaseReferenciaConverter implements AttributeConverter<FaseReferenciaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(FaseReferenciaEnum faseReferencia) {
        if (faseReferencia == null) {
            return null;
        }
        return faseReferencia.getId();
    }

    @Override
    public FaseReferenciaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return FaseReferenciaEnum.toEnum(id);
    }
}