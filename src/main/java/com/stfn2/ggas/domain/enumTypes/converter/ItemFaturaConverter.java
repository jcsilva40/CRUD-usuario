package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ItemFaturaConverter implements AttributeConverter<ItemFaturaEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(ItemFaturaEnum objeto) {
        if (objeto == null) {
            return null;
        }
        return objeto.getId();
    }

    @Override
    public ItemFaturaEnum convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return ItemFaturaEnum.toEnum(id);
    }
}
