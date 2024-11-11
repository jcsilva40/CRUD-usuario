package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.SegmentoCampanhaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SegmentoCampanhaConverter implements AttributeConverter<SegmentoCampanhaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(SegmentoCampanhaEnum segmentoCampanha) {
        if (segmentoCampanha == null) {
            return null;
        }
        return segmentoCampanha.getId();
    }

    @Override
    public SegmentoCampanhaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return SegmentoCampanhaEnum.toEnum(id);
    }
}