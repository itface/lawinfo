/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lawinfo.log;

import com.lawinfo.log.jul.JulWatcher;
import com.lawinfo.log.log4j.Log4jWatcher;
import com.lawinfo.log.util.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.List;
import java.util.Set;

/**
 * A Class to monitor Logging events and hold N events in memory
 * <p/>
 * This is abstract so we can support both JUL and Log4j (and other logging platforms)
 */
public abstract class LogWatcher {



    private static final Logger log = LoggerFactory.getLogger(LogWatcher.class);

    public static LogWatcher newRegisteredLogWatcher(Class<?> clazz) {
        String slf4jImpl;

        try {
            slf4jImpl = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
            log.info("SLF4J impl is " + slf4jImpl);
            if (clazz == null) {
                if ("org.slf4j.impl.Log4jLoggerFactory".equals(slf4jImpl)) {
                    clazz = LogType.LOG4J.getClazz();
                } else if (slf4jImpl.indexOf("JDK") > 0) {
                    clazz = LogType.JUL.getClazz();
                }
            }
        } catch (Exception e) {
            log.warn("Unable to read SLF4J version.  LogWatcher will be disabled: " + e);
            return null;
        }

        if (clazz == null) {
            log.info("No LogWatcher configured");
            return null;
        }

        if (clazz == LogType.LOG4J.getClazz()) {
            return new Log4jWatcher(slf4jImpl);
        }
        if (clazz == LogType.JUL.getClazz()) {
            return new JulWatcher(slf4jImpl);
        }
        return null;
    }

    /**
     * @return The implementation name
     */
    public abstract String getName();

    /**
     * @return The valid level names for this framework
     */
    public abstract List<String> getAllLevels();

    /**
     * Sets the log level within this framework
     */
    public abstract void setLogLevel(String category, String level);

//    /**
//     * @return all registered loggers
//     */
//    public abstract Collection<LoggerInfo> getAllLoggers();

    public abstract Set<Node> getLevels(String category);
}
