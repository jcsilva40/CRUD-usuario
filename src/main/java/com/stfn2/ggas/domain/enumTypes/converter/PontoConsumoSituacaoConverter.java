package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PontoConsumoSituacaoConverter implements AttributeConverter<PontoConsumoSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(PontoConsumoSituacaoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public PontoConsumoSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return PontoConsumoSituacaoEnum.toEnum(id);
    }
}
