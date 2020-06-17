package com.memsource.skeleton.service;

import com.memsource.skeleton.domain.dto.Data;
import com.memsource.skeleton.domain.dto.Options;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataExtractor {

    List<Data> extract(MultipartFile file, Options options);


}
