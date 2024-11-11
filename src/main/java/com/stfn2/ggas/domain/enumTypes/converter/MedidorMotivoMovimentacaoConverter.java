package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.MedidorMotivoMovimentacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MedidorMotivoMovimentacaoConverter implements AttributeConverter<MedidorMotivoMovimentacaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(MedidorMotivoMovimentacaoEnum medidorMotivoMovimentacaoEnum) {
        if (medidorMotivoMovimentacaoEnum == null) {
            return null;
        }
        return medidorMotivoMovimentacaoEnum.getId();
    }

    @Override
    public MedidorMotivoMovimentacaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return MedidorMotivoMovimentacaoEnum.toEnum(id);
    }
}
