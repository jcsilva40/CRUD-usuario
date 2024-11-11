package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CreditoDebitoSituacaoConverter  implements AttributeConverter<CreditoDebitoSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(CreditoDebitoSituacaoEnum corretorModeloEnum) {
        if (corretorModeloEnum == null) {
            return null;
        }
        return corretorModeloEnum.getId();
    }

    @Override
    public CreditoDebitoSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return CreditoDebitoSituacaoEnum.toEnum(id);
    }

}
