package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedicaoTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedicaoTipoConverter implements AttributeConverter<MedicaoTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedicaoTipoEnum medicaoTipo) {
        if (medicaoTipo == null) {
            return null;
        }
        return medicaoTipo.getId();
    }

    @Override
    public MedicaoTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedicaoTipoEnum.toEnum(id);
    }
}