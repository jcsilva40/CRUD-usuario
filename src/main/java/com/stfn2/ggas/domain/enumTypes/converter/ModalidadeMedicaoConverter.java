package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeMedicaoConverter implements AttributeConverter<ModalidadeMedicaoEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ModalidadeMedicaoEnum modalidadeMedicao) {
        if (modalidadeMedicao == null) {
            return null;
        }
        return modalidadeMedicao.getId();
    }

    @Override
    public ModalidadeMedicaoEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ModalidadeMedicaoEnum.toEnum(id);
    }
}