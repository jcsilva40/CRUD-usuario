package com.stfn2.ggas.domain.enumTypes.converter;
import com.stfn2.ggas.domain.enumTypes.TipoPeriodicidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPeriodicidadeConverter implements AttributeConverter<TipoPeriodicidadeEnum, Long> {

        @Override
        public Long convertToDatabaseColumn(TipoPeriodicidadeEnum objEnum) {
            if (objEnum == null) {
                return null;
            }
            return objEnum.getId();
        }

        @Override
        public TipoPeriodicidadeEnum convertToEntityAttribute(Long id) {
            if (id == null) {
                return null;
            }
            return TipoPeriodicidadeEnum.toEnum(id);
        }

}
