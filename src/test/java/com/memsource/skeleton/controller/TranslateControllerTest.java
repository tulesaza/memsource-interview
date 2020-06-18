package com.memsource.skeleton.controller;

import com.memsource.skeleton.service.TranslateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TranslateController.class)
class TranslateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TranslateService service;

    @BeforeEach
    void setup() {
        Mockito.when(service.translate(any(), any())).thenReturn("MOCK".getBytes());
    }

    @Test
    void whenValid_expected_HTTP200() throws Exception {


        byte[] fileContent = "TEST".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);

        byte[] json = "{\"sourceLanguage\": \"en\", \"targetLanguage\": \"cs\"}".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile jsonPart = new MockMultipartFile("options", "json", "application/json", json);


        MvcResult result = mockMvc.perform(multipart("/translate").file(filePart).file(jsonPart))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("MOCK", content);
    }
}