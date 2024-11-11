package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.CobrancaDebitoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CobrancaDebitoSituacaoConverter implements AttributeConverter<CobrancaDebitoSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(CobrancaDebitoSituacaoEnum cobrancaDebitoSituacao) {
        if (cobrancaDebitoSituacao == null) {
            return null;
        }
        return cobrancaDebitoSituacao.getId();
    }

    @Override
    public CobrancaDebitoSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return CobrancaDebitoSituacaoEnum.toEnum(id);
    }
}
