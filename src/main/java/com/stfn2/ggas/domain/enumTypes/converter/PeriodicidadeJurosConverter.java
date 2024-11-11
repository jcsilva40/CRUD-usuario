package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.PeriodicidadeJurosEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPeriodicidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PeriodicidadeJurosConverter implements AttributeConverter<PeriodicidadeJurosEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(PeriodicidadeJurosEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public PeriodicidadeJurosEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return PeriodicidadeJurosEnum.toEnum(id);
    }

}
