package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedidorSituacaoConverter implements AttributeConverter<MedidorSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorSituacaoEnum medidorSituacaoEnum) {
        if (medidorSituacaoEnum == null) {
            return null;
        }
        return medidorSituacaoEnum.getId();
    }

    @Override
    public MedidorSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorSituacaoEnum.toEnum(id);
    }
}
