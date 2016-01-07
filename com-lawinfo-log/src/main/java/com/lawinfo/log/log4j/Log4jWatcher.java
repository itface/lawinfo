/**
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
package com.lawinfo.log.log4j;


import com.lawinfo.log.LogWatcher;
import com.lawinfo.log.LoggerInfo;
import com.lawinfo.log.util.Node;
import com.lawinfo.log.util.Tree;
import org.apache.log4j.Level;

import java.util.*;

public class Log4jWatcher extends LogWatcher {

    public static final List<String> ALL_LEVELS = Arrays.asList(
            Level.DEBUG.toString(),
            Level.INFO.toString(),
            Level.WARN.toString(),
            Level.ERROR.toString(),
            Level.FATAL.toString());

    private final String name;

    private Tree tree;

    public Log4jWatcher(String name) {
        this.name = name;
        tree = initTree();
    }

    private Tree initTree() {
        Tree tree = new Tree();
        for (LoggerInfo loggerInfo : getAllLoggers()) {
            tree.addLoggerInfo(loggerInfo);
        }
        return tree;
    }

    @Override
    public String getName() {
        return "Log4j (" + name + ")";
    }

    @Override
    public List<String> getAllLevels() {
        return ALL_LEVELS;
    }

    @Override
    public void setLogLevel(String category, String level) {
        org.apache.log4j.Logger log;
        if (LoggerInfo.ROOT_NAME.equals(category)) {
            log = org.apache.log4j.LogManager.getRootLogger();
        } else {
            log = org.apache.log4j.Logger.getLogger(category);
        }
        if (level == null || "unset".equals(level) || "null".equals(level)) {
            log.setLevel(null);
        } else {
            log.setLevel(org.apache.log4j.Level.toLevel(level));
        }
        tree.setLogLevel(new Log4jInfo(log.getName(), log));
    }

    public Collection<LoggerInfo> getAllLoggers() {
        org.apache.log4j.Logger root = org.apache.log4j.LogManager.getRootLogger();
        Map<String, LoggerInfo> map = new HashMap<String, LoggerInfo>();
        Enumeration<?> loggers = org.apache.log4j.LogManager.getCurrentLoggers();
        while (loggers.hasMoreElements()) {
            org.apache.log4j.Logger logger = (org.apache.log4j.Logger) loggers.nextElement();
            if (logger == root) {
                continue;
            }
            String name = logger.getName();
            map.put(name, new Log4jInfo(name, logger));
            while (true) {
                int dot = name.lastIndexOf(".");
                if (dot < 0)
                    break;
                name = name.substring(0, dot);
                if (!map.containsKey(name)) {
                    map.put(name, new Log4jInfo(name, null));
                }
            }
        }
        map.put(LoggerInfo.ROOT_NAME, new Log4jInfo(LoggerInfo.ROOT_NAME, root));
        return new TreeSet<LoggerInfo>(map.values());
    }

    @Override
    public Set<Node> getLevels(String category) {
        return tree.getLevels(category);
    }
}