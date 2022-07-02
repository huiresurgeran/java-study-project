package com.jsamuel.study.generic.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class GenericType<T> {

    private static final Logger logger = LoggerFactory.getLogger(GenericType.class);

    private T data;

    public static void main(String[] args) {
        GenericType<String> genericType = new GenericType<String>() {
        };
        Type superClass = genericType.getClass().getGenericSuperclass();

        // getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
        Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
        for (Type type : types) {
            logger.info("type: {}", type);
        }

        // getRawType 返回当前class或interface声明的类型, 如List<?>返回List
        Type rawType = ((ParameterizedType) superClass).getRawType();
        logger.info("rawType: {}", rawType);

        // getOwnerType 返回所属类型. 如,当前类型为O<T>.I<S>, 则返回O<T>. 顶级类型将返回null
        Type ownerType = ((ParameterizedType) superClass).getOwnerType();
        logger.info("ownerType: {}", ownerType);
    }
}
