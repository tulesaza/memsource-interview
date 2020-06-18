package com.memsource.skeleton.annotation;

import com.memsource.skeleton.domain.enums.TranslatorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TranslateEngine {
    TranslatorType type();
}
