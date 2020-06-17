package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Options;
import org.springframework.web.multipart.MultipartFile;

public interface TranslateService {

    //TODO should be specific exception
    byte[] translate(Options options, MultipartFile file);
}
