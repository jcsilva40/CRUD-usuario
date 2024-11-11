package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.LogCampanhaDescontoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LogCampanhaDescontoConverter implements AttributeConverter<LogCampanhaDescontoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(LogCampanhaDescontoEnum logCampanhaDesconto) {
        if (logCampanhaDesconto == null) {
            return null;
        }
        return logCampanhaDesconto.getId();
    }

    @Override
    public LogCampanhaDescontoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return LogCampanhaDescontoEnum.toEnum(id);
    }
}