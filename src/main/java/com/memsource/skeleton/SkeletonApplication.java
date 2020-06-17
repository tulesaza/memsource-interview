package com.memsource.skeleton;

import org.jetbrains.annotations.Nullable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkeletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkeletonApplication.class, args);
    }

    @Nullable
    public static String getAppVersion() {
        return SkeletonApplication.class.getPackage().getImplementationVersion();
    }

}
