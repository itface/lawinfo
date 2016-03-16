package com.lawinfo.domain.sourcegenerator.annotation;

import java.lang.annotation.*;

/**
 * Created by wangrongtao on 16/3/16.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormGlobalSetting {
    String tableCaption();
    String baseurl();
}
