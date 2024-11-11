package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoPagamentoConverter implements AttributeConverter<SituacaoPagamentoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(SituacaoPagamentoEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public SituacaoPagamentoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return SituacaoPagamentoEnum.toEnum(id);
    }
}