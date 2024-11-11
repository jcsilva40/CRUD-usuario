package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ImovelTipoMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.ImovelTipoMedicaoGLPEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ImovelTipoMedicaoGLPConverter implements AttributeConverter<ImovelTipoMedicaoGLPEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ImovelTipoMedicaoGLPEnum imovelTipoMedicaoGLPEnum) {
      if (imovelTipoMedicaoGLPEnum == null) {
         return null;
      }
      return imovelTipoMedicaoGLPEnum.getId();
   }

   @Override
   public ImovelTipoMedicaoGLPEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ImovelTipoMedicaoGLPEnum.toEnum(id);
   }

}