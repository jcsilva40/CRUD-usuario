package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ClienteImovelRelacionamentoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClienteImovelRelacionamentoConverter implements AttributeConverter<ClienteImovelRelacionamentoEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ClienteImovelRelacionamentoEnum clienteImovelRelacionamentoEnum) {
      if (clienteImovelRelacionamentoEnum == null) {
         return null;
      }
      return clienteImovelRelacionamentoEnum.getId();
   }

   @Override
   public ClienteImovelRelacionamentoEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ClienteImovelRelacionamentoEnum.toEnum(id);
   }

}