package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusCampanhaConverter implements AttributeConverter<StatusCampanhaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusCampanhaEnum statusCampanha) {
        if (statusCampanha == null) {
            return null;
        }
        return statusCampanha.getId();
    }

    @Override
    public StatusCampanhaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return StatusCampanhaEnum.toEnum(id);
    }
}