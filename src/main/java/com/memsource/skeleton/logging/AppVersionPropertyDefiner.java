package com.memsource.skeleton.logging;

import ch.qos.logback.core.PropertyDefinerBase;
import com.memsource.skeleton.SkeletonApplication;
import org.springframework.context.ApplicationContextException;

public class AppVersionPropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        String appVersion = SkeletonApplication.getAppVersion();
        if (appVersion == null) {
            throw new ApplicationContextException("Cannot get Implementation-Version from MANIFEST.MF");
        }
        return appVersion;
    }

}
