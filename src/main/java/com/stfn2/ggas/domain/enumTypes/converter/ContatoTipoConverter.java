package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ContatoTipoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContatoTipoConverter implements AttributeConverter<ContatoTipoEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ContatoTipoEnum contatoTipoEnum) {
      if (contatoTipoEnum == null) {
         return null;
      }
      return contatoTipoEnum.getId();
   }

   @Override
   public ContatoTipoEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ContatoTipoEnum.toEnum(id);
   }

}