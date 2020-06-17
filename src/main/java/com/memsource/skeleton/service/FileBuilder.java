package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileBuilder {

    byte[] buildTranslated(Options options, MultipartFile file, List<Data> data);

}
