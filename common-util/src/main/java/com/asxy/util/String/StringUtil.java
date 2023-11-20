package com.asxy.util.String;

import java.lang.reflect.Field;

/**
 * @author asxy
 */
public class StringUtil {

    /**
     * 把对象中的 String 类型的null字段，转换为空字符串
     *
     * @param <T> 待转化对象类型
     * @param cls 待转化对象
     * @return 转化好的对象
     */
    public static <T> T noNullStringAttr(T cls) {
        Field[] fields = cls.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return cls;
        }
        for (Field field : fields) {
            if ("String".equals(field.getType().getSimpleName())) {
                field.setAccessible(true);
                try {
                    Object value = field.get(cls);
                    if (value == null) {
                        field.set(cls, "");
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return cls;
    }


    
}
