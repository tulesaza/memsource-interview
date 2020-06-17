package com.memsource.skeleton.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Options {
    @JsonProperty("sourceLanguage")
    private String sourceLanguage;

    @JsonProperty("targetLanguage")
    private String targetLanguage;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
}
