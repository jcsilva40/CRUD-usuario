package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.SuperMedicaoAnormalidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SuperMedicaoAnormalidadeConverter implements AttributeConverter<SuperMedicaoAnormalidadeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(SuperMedicaoAnormalidadeEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public SuperMedicaoAnormalidadeEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return SuperMedicaoAnormalidadeEnum.toEnum(id);
    }
}