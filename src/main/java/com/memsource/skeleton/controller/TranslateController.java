package com.memsource.skeleton.controller;


import com.memsource.skeleton.domain.dto.Options;
import com.memsource.skeleton.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//TODO add swagger annotations
public class TranslateController {

    private final TranslateService translateService;

    @Autowired
    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE)
    public byte[] translate(@RequestPart("options") Options options,
                            @RequestPart("file") MultipartFile file) {

        return translateService.translate(options, file);

    }

}
