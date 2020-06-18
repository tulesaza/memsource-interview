package com.memsource.skeleton.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmazonTranslateEngineApiServiceTest {
    private final TranslateEngineApiService service = new AmazonTranslateEngineApiService();

    //TODO in case of more complicated logic should be extenede by more tescases

    @ParameterizedTest
    @CsvSource({
            "HelloWorld, HelloWorldTranslatedByAmazon",
            "Memsource, MemsourceTranslatedByAmazon"
    })
    void translate(String tested, String expected) {
        assertEquals(expected, service.translate(tested));


    }
}