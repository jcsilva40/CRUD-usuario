package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ProtocoloComunicacaoDado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProtocoloComunicacaoDadoConverter implements AttributeConverter<ProtocoloComunicacaoDado, Long> {

    @Override
    public Long convertToDatabaseColumn(ProtocoloComunicacaoDado protocoloComunicacaoDado) {
        if (protocoloComunicacaoDado == null) {
            return null;
        }
        return protocoloComunicacaoDado.getId();
    }

    @Override
    public ProtocoloComunicacaoDado convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ProtocoloComunicacaoDado.toEnum(id);
    }
}
