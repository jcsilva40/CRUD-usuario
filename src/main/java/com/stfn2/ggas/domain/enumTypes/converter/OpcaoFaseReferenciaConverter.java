package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.OpcaoFaseReferenciaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OpcaoFaseReferenciaConverter implements AttributeConverter<OpcaoFaseReferenciaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(OpcaoFaseReferenciaEnum opcaoFaseReferencia) {
        if (opcaoFaseReferencia == null) {
            return null;
        }
        return opcaoFaseReferencia.getId();
    }

    @Override
    public OpcaoFaseReferenciaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return OpcaoFaseReferenciaEnum.toEnum(id);
    }
}