package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCarteiraCobrancaConverter implements AttributeConverter<TipoCarteiraCobrancaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoCarteiraCobrancaEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoCarteiraCobrancaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoCarteiraCobrancaEnum.toEnum(id);
    }
}
