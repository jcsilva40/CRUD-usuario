package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorLocalInstalacaoEnum;
import jakarta.persistence.AttributeConverter;

public class MedidorLocalInstalacaoConverter implements AttributeConverter<MedidorLocalInstalacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorLocalInstalacaoEnum medidorLocalInstalacaoEnum) {
        if (medidorLocalInstalacaoEnum == null) {
            return null;
        }
        return medidorLocalInstalacaoEnum.getId();
    }

    @Override
    public MedidorLocalInstalacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorLocalInstalacaoEnum.toEnum(id);
    }
}
