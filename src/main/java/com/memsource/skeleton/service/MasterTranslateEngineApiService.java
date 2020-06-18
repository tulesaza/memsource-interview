package com.memsource.skeleton.service;

import com.memsource.skeleton.annotation.TranslatorsContainer;
import com.memsource.skeleton.domain.enums.TranslatorType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MasterTranslateEngineApiService {

    @TranslatorsContainer
    private EnumMap<TranslatorType, TranslateEngineApiService> translatorsMap;


    public String translate(String sourceSentence, TranslatorType type) {
        return translatorsMap.get(type).translate(sourceSentence);
    }

    public ConcurrentHashMap<String, String> translateByEngines(String sourceSentence, List<String> engineNames) {
        //TODO create executor with fixed threadPool and fork-join submitter
        // NOTE: it's out of scope this task

        throw new RuntimeException("Not implemented yet");
    }
}
