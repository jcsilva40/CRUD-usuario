package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAplicacaoConverter implements AttributeConverter<TipoAplicacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoAplicacaoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoAplicacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoAplicacaoEnum.toEnum(id);
    }
}
