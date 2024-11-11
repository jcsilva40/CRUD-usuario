package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorProtecaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedidorProtecaoConverter implements AttributeConverter<MedidorProtecaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorProtecaoEnum medidorProtecaoEnum) {
        if (medidorProtecaoEnum == null) {
            return null;
        }
        return medidorProtecaoEnum.getId();
    }

    @Override
    public MedidorProtecaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorProtecaoEnum.toEnum(id);
    }
}
