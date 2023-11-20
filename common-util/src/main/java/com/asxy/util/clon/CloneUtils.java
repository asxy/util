package com.asxy.util.clon;


import com.asxy.util.json.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 克隆工具类
 *
 * 使用该拷贝工具请按照一下三类进行划分：
 * 1. 不涉及泛型类型、源和目标类型一致：R deepClone(R value)
 * 2. 不涉及泛型类型、源和目标类型不同：R deepClone(P value, Class<R> valueType)
 * 3. 啥都能干(不会选就选我了)：R deepClone(P value, TypeReference<R> valueType)
 *
 * @author asxy
 */
public class CloneUtils {


    /**
     * 深拷贝对象
     * 特别注意：该方法不支持泛型类型的深拷贝，如果有泛型需求请使用其他重载方法
     *
     * @param value 被拷贝的对象
     * @param <R>   拷贝对象类型
     * @return 拷贝结果
     */
    public static <R> R deepClone(R value) {
        if (Objects.isNull(value)) {
            return null;
        }
        Type generic = value.getClass().getGenericSuperclass();
        if (generic instanceof ParameterizedType) {
            throw new RuntimeException("不支持泛型类型进行深拷贝，请参考其他重载方法");
        } else {
            String content = JsonUtils.writeValueAsString(value);
            return JsonUtils.readValue(content, (Class<R>) value.getClass());
        }
    }

    /**
     * 深拷贝对象，支持不同类型之间的深拷贝
     * 特别注意：该方法不支持泛型类型的深拷贝，如果有泛型需求请使用其他重载方法
     *
     * @param value     被拷贝的对象
     * @param valueType 拷贝结果类型
     * @param <P>       被拷贝的对象类型
     * @param <R>       拷贝结果类型
     * @return 拷贝结果
     */
    public static <P, R> R deepClone(P value, Class<R> valueType) {
        String content = JsonUtils.writeValueAsString(value);
        return JsonUtils.readValue(content, valueType);
    }

    /**
     * 深拷贝对象，支持泛型类型
     *
     * @param value     被拷贝的对象
     * @param valueType 拷贝结果类型
     * @param <P>       被拷贝的对象类型
     * @param <R>       拷贝结果类型
     * @return 拷贝结果
     */
    public static <P, R> R deepClone(P value, TypeReference<R> valueType) {
        String content = JsonUtils.writeValueAsString(value);
        return JsonUtils.readValue(content, valueType);
    }

}
