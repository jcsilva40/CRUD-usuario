package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedidorTipoConverter implements AttributeConverter<MedidorTipoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorTipoEnum medidorTipoEnum) {
        if (medidorTipoEnum == null) {
            return null;
        }
        return medidorTipoEnum.getId();
    }

    @Override
    public MedidorTipoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorTipoEnum.toEnum(id);
    }
}
