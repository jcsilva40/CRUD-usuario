package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ConsumoComposicaoTipo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConsumoComposicaoTipoConverter implements AttributeConverter<ConsumoComposicaoTipo, Long> {

    @Override
    public Long convertToDatabaseColumn(ConsumoComposicaoTipo consumoComposicaoTipo) {
        if (consumoComposicaoTipo == null) {
            return null;
        }
        return consumoComposicaoTipo.getId();
    }

    @Override
    public ConsumoComposicaoTipo convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ConsumoComposicaoTipo.toEnum(id);
    }
}
