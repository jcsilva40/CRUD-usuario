package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoPrimitivoParametroBIEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPrimitivoParametroBIConverter implements AttributeConverter<TipoPrimitivoParametroBIEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoPrimitivoParametroBIEnum tipoPrimitivoParametroBI) {
        if (tipoPrimitivoParametroBI == null) {
            return null;
        }
        return tipoPrimitivoParametroBI.getId();
    }

    @Override
    public TipoPrimitivoParametroBIEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoPrimitivoParametroBIEnum.toEnum(id);
    }
}