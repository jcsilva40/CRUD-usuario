package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.EsferaPoderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EsferaPoderConverter implements AttributeConverter<EsferaPoderEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(EsferaPoderEnum esferaPoder) {
      if (esferaPoder == null) {
         return null;
      }
      return esferaPoder.getId();
   }

   @Override
   public EsferaPoderEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return EsferaPoderEnum.toEnum(id);
   }

}