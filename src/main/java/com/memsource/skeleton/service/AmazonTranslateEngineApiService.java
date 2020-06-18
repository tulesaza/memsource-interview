package com.memsource.skeleton.service;

import com.memsource.skeleton.annotation.TranslateEngine;
import com.memsource.skeleton.domain.enums.TranslatorType;
import org.springframework.stereotype.Service;

@Service
@TranslateEngine(type = TranslatorType.AMAZON)
public class AmazonTranslateEngineApiService implements TranslateEngineApiService {

    @Override
    public String translate(String source) {
        return String.format("%sTranslatedByAmazon", source);
    }


}
