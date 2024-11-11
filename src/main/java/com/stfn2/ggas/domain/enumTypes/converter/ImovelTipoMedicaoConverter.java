package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ImovelOrigemEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelTipoMedicaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ImovelTipoMedicaoConverter implements AttributeConverter<ImovelTipoMedicaoEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ImovelTipoMedicaoEnum imovelTipoMedicaoEnum) {
      if (imovelTipoMedicaoEnum == null) {
         return null;
      }
      return imovelTipoMedicaoEnum.getId();
   }

   @Override
   public ImovelTipoMedicaoEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ImovelTipoMedicaoEnum.toEnum(id);
   }

}