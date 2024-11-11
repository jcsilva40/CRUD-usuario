package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ImovelSituacaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ImovelSituacaoConverter implements AttributeConverter<ImovelSituacaoEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ImovelSituacaoEnum imovelSituacaoEnum) {
      if (imovelSituacaoEnum == null) {
         return null;
      }
      return imovelSituacaoEnum.getId();
   }

   @Override
   public ImovelSituacaoEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ImovelSituacaoEnum.toEnum(id);
   }

}