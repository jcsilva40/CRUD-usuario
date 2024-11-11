package com.stfn2.ggas.domain.enumTypes.converter;

import com.stfn2.ggas.domain.enumTypes.ClienteImovelFimRelacionamentoMotivoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClienteImovelFimRelacionamentoMotivoConverter implements AttributeConverter<ClienteImovelFimRelacionamentoMotivoEnum, Long> {

   @Override
   public Long convertToDatabaseColumn(ClienteImovelFimRelacionamentoMotivoEnum clienteImovelFimRelacionamentoMotivoEnum) {
      if (clienteImovelFimRelacionamentoMotivoEnum == null) {
         return null;
      }
      return clienteImovelFimRelacionamentoMotivoEnum.getId();
   }

   @Override
   public ClienteImovelFimRelacionamentoMotivoEnum convertToEntityAttribute(Long id) {
      if (id == null) {
         return null;
      }
      return ClienteImovelFimRelacionamentoMotivoEnum.toEnum(id);
   }

}