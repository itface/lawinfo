package com.lawinfo.log;

import com.lawinfo.log.jul.JulWatcher;
import com.lawinfo.log.log4j.Log4jWatcher;


public enum LogType {
    LOG4J(Log4jWatcher.class),
    JUL(JulWatcher.class);

    LogType(Class<?> clazz) {
        this.clazz = clazz;
    }

    private Class<?> clazz;

    public Class<?> getClazz() {
        return clazz;
    }
}
