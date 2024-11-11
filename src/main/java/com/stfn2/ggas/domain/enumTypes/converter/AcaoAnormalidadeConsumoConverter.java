package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.AcaoAnormalidadeConsumoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AcaoAnormalidadeConsumoConverter implements AttributeConverter<AcaoAnormalidadeConsumoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(AcaoAnormalidadeConsumoEnum acaoAnormalidadeConsumo) {
        if (acaoAnormalidadeConsumo == null) {
            return null;
        }
        return acaoAnormalidadeConsumo.getId();
    }

    @Override
    public AcaoAnormalidadeConsumoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return AcaoAnormalidadeConsumoEnum.toEnum(id);
    }
}