package com.memsource.skeleton.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileBuilderTest {

    private final FileBuilder fileBuilder = new JsonFileBuilder();

    private final Options options = new Options("en", "cs");


    @Test
    void buildTranslated() throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("en", "Mark Twain");
        map.put("cs", "Mark TwainCZ");

        Data data = new Data("$['book']['chapters'][0]['segments'][0]['en']",
                "$['book']['chapters'][0]['segments'][0]['cs']",
                map);

        List<Data> input = new ArrayList<>();
        input.add(data);

        ClassPathResource source = new ClassPathResource("another-sample.json");
        MultipartFile multipartFile = new MockMultipartFile(Objects.requireNonNull(source.getFilename()),
                source.getInputStream());
        byte[] result = fileBuilder.buildTranslated(options, multipartFile, input);
        DocumentContext context = JsonPath.parse(new String(result));
        assertEquals("Mark TwainCZ", context.read("$['book']['chapters'][0]['segments'][0]['cs']"));
    }
}