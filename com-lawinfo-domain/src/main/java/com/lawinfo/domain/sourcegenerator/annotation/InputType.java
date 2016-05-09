package com.lawinfo.domain.sourcegenerator.annotation;

import java.lang.annotation.*;

/**
 * Created by wangrongtao on 16/3/16.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InputType {
    InputType.type value() default type.text;
    String name();
    boolean queryAble() default false;
    boolean showInList() default false;
    boolean showInEditForm() default false;
    boolean showInReadForm() default false;
    public enum type {
        text,
        select,
        textarea,
        checkbox,
        radio,
        hidden,
        date
    }
}
