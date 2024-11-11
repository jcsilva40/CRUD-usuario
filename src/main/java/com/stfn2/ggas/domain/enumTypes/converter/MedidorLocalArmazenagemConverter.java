package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorLocalArmazenagemEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedidorLocalArmazenagemConverter implements AttributeConverter<MedidorLocalArmazenagemEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorLocalArmazenagemEnum medidorLocalArmazenagemEnum) {
        if (medidorLocalArmazenagemEnum == null) {
            return null;
        }
        return medidorLocalArmazenagemEnum.getId();
    }

    @Override
    public MedidorLocalArmazenagemEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorLocalArmazenagemEnum.toEnum(id);
    }
}
