package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoDocumentoImpressoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDocumentoImpressoConverter implements AttributeConverter<TipoDocumentoImpressoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoDocumentoImpressoEnum objEnum) {
        if (objEnum == null) {
            return null;
        }
        return objEnum.getId();
    }

    @Override
    public TipoDocumentoImpressoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoDocumentoImpressoEnum.toEnum(id);
    }
}