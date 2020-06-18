package com.memsource.skeleton.infrastructure;

import com.memsource.skeleton.annotation.TranslateEngine;
import com.memsource.skeleton.annotation.TranslatorsContainer;
import com.memsource.skeleton.domain.enums.TranslatorType;
import com.memsource.skeleton.service.TranslateEngineApiService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.EnumMap;

@Component
public class TranslateEngineBeanPostProcessor implements BeanPostProcessor {

    EnumMap<TranslatorType, TranslateEngineApiService> map = new EnumMap<>(TranslatorType.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NotNull String beanName) throws BeansException {

        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(TranslateEngine.class) && bean instanceof TranslateEngineApiService) {
            TranslateEngine annotation = beanClass.getAnnotation(TranslateEngine.class);
            TranslatorType type = annotation.type();
            map.put(type, (TranslateEngineApiService) bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            TranslatorsContainer annotation = field.getAnnotation(TranslatorsContainer.class);
            if (annotation != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, map);
            }
        }
        return bean;
    }
}
