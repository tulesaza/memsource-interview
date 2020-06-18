package com.memsource.skeleton.service;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonFileDataExtractor implements DataExtractor {
    @Override
    public List<Data> extract(MultipartFile file, Options options) {
        List<Data> result = new ArrayList<>();
        Configuration configuration = Configuration.builder().options(Option.AS_PATH_LIST).build();

        String getSourceLangExpression = String.format("$..%s", options.getSourceLanguage());
        String getTargetLangExpression = String.format("$..%s", options.getTargetLanguage());

        try {
            DocumentContext pathContext = JsonPath.using(configuration).parse(file.getInputStream());
            DocumentContext valuesContext = JsonPath.parse(file.getInputStream());
            List<String> sourceLangPaths = pathContext.read(getSourceLangExpression);
            List<String> targetLangPaths = pathContext.read(getTargetLangExpression);
            sourceLangPaths.forEach(path -> {
                String possibleTargetPath = replaceLangInPath(path, options.getSourceLanguage(), options.getTargetLanguage());
                if (targetLangPaths.contains(possibleTargetPath)) {
                    Map<String, String> data = new HashMap<>(2);
                    data.put(options.getSourceLanguage(), valuesContext.read(path));
                    result.add(new Data(path, possibleTargetPath, data));
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    private String replaceLangInPath(String path, String oldLang, String newLang) {
        String oldValue = String.format("['%s']", oldLang);
        String newValue = String.format("['%s']", newLang);
        return path.replace(oldValue, newValue);
    }

}
