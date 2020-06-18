package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileDataExtractorTest {

    private final DataExtractor extractor = new JsonFileDataExtractor();

    private final Options options = new Options("en", "cs");

    @Test
    void extract_from_sample_file() throws IOException {
        ClassPathResource source = new ClassPathResource("sample.json");
        MultipartFile multipartFile = new MockMultipartFile(Objects.requireNonNull(source.getFilename()), source.getInputStream());

        List<Data> result = extractor.extract(multipartFile, options);
        assertEquals(6, result.size());
        //TODO better checks for values, etc
    }

    @Test
    void exctract_from_anther_sample_file() throws IOException {
        ClassPathResource source = new ClassPathResource("another-sample.json");

        MultipartFile multipartFile = new MockMultipartFile(Objects.requireNonNull(source.getFilename()), source.getInputStream());
        List<Data> result = extractor.extract(multipartFile, options);
        assertEquals(1, result.size());
        //TODO better checks for values, etc
    }


}