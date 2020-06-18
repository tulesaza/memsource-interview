package com.memsource.skeleton.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoogleTranslateEngineApiServiceTest {
    private final TranslateEngineApiService service = new GoogleTranslateEngineApiService();

    //TODO in case of more complicated logic should be extenede by more tescases

    @ParameterizedTest
    @CsvSource({
            "HelloWorld, HelloWorldTranslatedByGoogle",
            "Memsource, MemsourceTranslatedByGoogle"
    })
    void translate(String tested, String expected) {
        assertEquals(expected, service.translate(tested));


    }
}