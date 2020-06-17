package com.memsource.skeleton.service;

import org.springframework.stereotype.Service;

@Service
public class MockTranslateEngineApiService implements TranslateEngineApiService {
    @Override
    public String translate(String source) {
        return String.format("%sTranslated", source);
    }
}
