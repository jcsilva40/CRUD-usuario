package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ModalidadeUsoEnum;
import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeUsoConverter implements AttributeConverter<ModalidadeUsoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ModalidadeUsoEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public ModalidadeUsoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ModalidadeUsoEnum.toEnum(id);
    }
}
