package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import com.memsource.skeleton.domain.enums.TranslatorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

//TODO more tests :)
@SpringBootTest(classes = {DefaultTranslateService.class})
class DefaultTranslateServiceTest {

    @Autowired
    TranslateService translateService;

    @MockBean
    DataExtractor extractor;

    @MockBean
    MasterTranslateEngineApiService engineApiService;

    @MockBean
    FileBuilder fileBuilder;

    @BeforeEach
    public void setup() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("en", "Mark Twain");
        map.put("cs", "Mark TwainCZ");

        Data data = new Data("$['book']['chapters'][0]['segments'][0]['en']",
                "$['book']['chapters'][0]['segments'][0]['cs']",
                map);

        List<Data> input = new ArrayList<>();
        input.add(data);

        Mockito.when(extractor.extract(any(MultipartFile.class), any(Options.class))).thenReturn(input);
        Mockito.when(engineApiService.translate(anyString(), any(TranslatorType.class))).thenReturn("MOCK");
        Mockito.when(fileBuilder.buildTranslated(
                any(Options.class),
                any(MultipartFile.class),
                any())).thenReturn("TEST".getBytes());

    }

    @Test
    void translate() throws IOException {

        Options options = new Options("en", "cs");

        ClassPathResource source = new ClassPathResource("another-sample.json");
        MultipartFile multipartFile = new MockMultipartFile(Objects.requireNonNull(source.getFilename()),
                source.getInputStream());
        String result = new String(translateService.translate(options, multipartFile));
        assertEquals("TEST", result);

    }
}