package com.memsource.skeleton.domain.dto;

import java.util.Map;

public class Data {

    private String sourcePath;

    private String targetPath;


    private Map<String, String> data;

    public Data(String sourcePath, String targetPath, Map<String, String> data) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.data = data;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }


    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
