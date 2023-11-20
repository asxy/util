package com.asxy.util.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

public class SpringBeanUtils {
    private static ConfigurableApplicationContext context = null;

    public static void setContext(ConfigurableApplicationContext context) {
        SpringBeanUtils.context = context;
    }

    public static ConfigurableApplicationContext getContext() {
        return SpringBeanUtils.context;
    }

    private static void check() {
        if (Objects.isNull(context)) {
            throw new RuntimeException(String.format("请检查启动类是否覆盖(%s)Bean的注入", SpringBeanUtils.class.getName()));
        }
    }

    /**
     * Return the bean instance that uniquely matches the given object type, if any.
     *
     * @param requiredType type the bean must match; can be an interface or superclass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        check();
        return context.getBean(requiredType);
    }

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param name the name of the bean to retrieve
     * @return
     */
    public static Object getBean(String name) {
        check();
        return context.getBean(name);
    }
}
