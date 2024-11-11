package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.TipoVinculoContratoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoVinculoContratoConverter implements AttributeConverter<TipoVinculoContratoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoVinculoContratoEnum tipoVinculoContrato) {
        if (tipoVinculoContrato == null) {
            return null;
        }
        return tipoVinculoContrato.getId();
    }

    @Override
    public TipoVinculoContratoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return TipoVinculoContratoEnum.toEnum(id);
    }
}