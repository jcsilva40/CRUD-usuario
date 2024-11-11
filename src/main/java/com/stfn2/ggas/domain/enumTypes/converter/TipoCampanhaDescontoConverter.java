package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoCampanhaDescontoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCampanhaDescontoConverter implements AttributeConverter<TipoCampanhaDescontoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoCampanhaDescontoEnum tipoCampanhaDesconto) {
        if (tipoCampanhaDesconto == null) {
            return null;
        }
        return tipoCampanhaDesconto.getId();
    }

    @Override
    public TipoCampanhaDescontoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoCampanhaDescontoEnum.toEnum(id);
    }
}