package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.AmortizacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPeriodicidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AmortizacaoConverter implements AttributeConverter <AmortizacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(AmortizacaoEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public AmortizacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return AmortizacaoEnum.toEnum(id);
    }


}
