package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.RecebimentoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RecebimentoSituacaoConverter implements AttributeConverter<RecebimentoSituacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(RecebimentoSituacaoEnum recebimentoSituacao) {
        if (recebimentoSituacao == null) {
            return null;
        }
        return recebimentoSituacao.getId();
    }

    @Override
    public RecebimentoSituacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return RecebimentoSituacaoEnum.toEnum(id);
    }
}
