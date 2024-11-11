package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.FormaCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.FormaCobrancaParcelamentoEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorMotivoMovimentacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FormaCobrancaParcelamentoConverter implements AttributeConverter<FormaCobrancaParcelamentoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(FormaCobrancaParcelamentoEnum formacobranca) {
        if (formacobranca == null) {
            return null;
        }
        return formacobranca.getId();
    }

    @Override
    public FormaCobrancaParcelamentoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return FormaCobrancaParcelamentoEnum.toEnum(id);
    }

}
