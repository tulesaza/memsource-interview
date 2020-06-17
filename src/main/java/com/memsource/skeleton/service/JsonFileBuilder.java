package com.memsource.skeleton.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class JsonFileBuilder implements FileBuilder {
    @Override
    public byte[] buildTranslated(Options options, MultipartFile file, List<Data> data) {

        try {
            DocumentContext valuesContext = JsonPath.parse(file.getInputStream());
            data.forEach(sentence -> {
                valuesContext.set(sentence.getTargetPath(), sentence.getData().get(options.getTargetLanguage()));
            });
            return valuesContext.jsonString().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
