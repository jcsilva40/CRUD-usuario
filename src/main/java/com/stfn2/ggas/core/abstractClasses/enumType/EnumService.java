package com.stfn2.ggas.core.abstractClasses.enumType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.stereotype.Service;

@Service
public class EnumService {

    public String findDescricaoEnum(String enumName, Long id) {

        return getValueEnum(enumName, id, "descricao");
    }
    
    public String findAbreviadoEnum(String enumName, Long id) {

        return getValueEnum(enumName, id, "abreviado");
    }

    private String getValueEnum(String enumName, Long id, String campoARetornar) {
        String descricao = null;
        try {
            Class<?> enumClass = Class.forName("com.stfn2.ggas.domain.enumTypes." + enumName);

            if (enumClass.isEnum()) {
                for (Object enumConstant : enumClass.getEnumConstants()) {
                    Method toEnumMethod = enumClass.getMethod("toEnum", Long.class);
                    Object enumObj = toEnumMethod.invoke(null, id);

                    if (enumObj.equals(enumConstant)) {
                        for (Field field : enumClass.getDeclaredFields()) {
                            if (!field.isSynthetic()) {
                                field.setAccessible(true);
                                if (field.getName().equals(campoARetornar)) {
                                    descricao = (String) field.get(enumConstant);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return descricao;
    }
}
