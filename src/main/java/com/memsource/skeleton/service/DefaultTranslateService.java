package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import com.memsource.skeleton.domain.enums.TranslatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
//TODO according that users usually translate file in sessions, make sense to persist file in mongo or postgres jsonb
// due to dynamic structure
public class DefaultTranslateService implements TranslateService {

    //TODO to support different file extensions can be multiple implementations
    private final DataExtractor dataExtractor;

    private final MasterTranslateEngineApiService masterTranslateEngine;

    //TODO to support different file extensions can be multiple implementations
    private final FileBuilder fileBuilder;

    @Autowired
    public DefaultTranslateService(DataExtractor dataExtractor,
                                   MasterTranslateEngineApiService masterTranslateEngine,
                                   FileBuilder fileBuilder) {
        this.dataExtractor = dataExtractor;
        this.masterTranslateEngine = masterTranslateEngine;
        this.fileBuilder = fileBuilder;
    }

    @Override
    //TODO at the moment which translator should be used is hardcoded but can be added to func signature
    // or for example if no concrete translator is provided use same default based on config
    public byte[] translate(Options options, MultipartFile file) {

        List<Data> data = dataExtractor.extract(file, options);
        data.forEach(entry -> {
            Map<String, String> map = entry.getData();
            map.put(
                    options.getTargetLanguage(),
                    masterTranslateEngine.translate(map.get(options.getSourceLanguage()), TranslatorType.AMAZON)
            );
        });
        return fileBuilder.buildTranslated(options, file, data);
    }
}
