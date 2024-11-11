package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ImovelOrigemEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ImovelOrigemConverter implements AttributeConverter<ImovelOrigemEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ImovelOrigemEnum imovelOrigemEnum) {
      if (imovelOrigemEnum == null) {
         return null;
      }
      return imovelOrigemEnum.getId();
   }

   @Override
   public ImovelOrigemEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ImovelOrigemEnum.toEnum(id);
   }

}