package com.application.crud_usuario.utils;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
public class CustomBeanUtils {
    public static void copyNonNullProperties(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] getNullPropertyNames(Object source) {
        Field[] allFields = source.getClass().getDeclaredFields();
        return java.util.Arrays.stream(allFields)
                .filter(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(source) == null;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .map(Field::getName)
                .toArray(String[]::new);
    }
}
