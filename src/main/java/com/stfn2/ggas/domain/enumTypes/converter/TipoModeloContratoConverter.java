package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoModeloContratoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoModeloContratoConverter implements AttributeConverter<TipoModeloContratoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoModeloContratoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public TipoModeloContratoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoModeloContratoEnum.toEnum(id);
    }
}
