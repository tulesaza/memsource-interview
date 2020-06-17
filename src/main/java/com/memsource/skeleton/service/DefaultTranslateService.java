package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
public class DefaultTranslateService implements TranslateService {

    private final DataExtractor dataExtractor;

    private final TranslateEngineApiService translateEngineApiService;

    private final FileBuilder fileBuilder;

    @Autowired
    public DefaultTranslateService(DataExtractor dataExtractor,
                                   TranslateEngineApiService translateEngineApiService,
                                   FileBuilder fileBuilder) {
        this.dataExtractor = dataExtractor;
        this.translateEngineApiService = translateEngineApiService;
        this.fileBuilder = fileBuilder;
    }

    @Override
    public byte[] translate(Options options, MultipartFile file) {

        List<Data> data = dataExtractor.extract(file, options);
        data.forEach(entry -> {
            Map<String, String> map = entry.getData();
            map.put(
                    options.getTargetLanguage(),
                    translateEngineApiService.translate(map.get(options.getSourceLanguage()))
            );
        });
        return fileBuilder.buildTranslated(options, file, data);
    }
}
