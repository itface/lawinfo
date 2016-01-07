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
package com.lawinfo.log.jul;


import com.lawinfo.log.LogWatcher;
import com.lawinfo.log.LoggerInfo;
import com.lawinfo.log.util.Node;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JulWatcher extends LogWatcher {

    final String name;

    public JulWatcher(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "JUL (" + name + ")";
    }


    @Override
    public List<String> getAllLevels() {
        return Arrays.asList(
                Level.FINEST.getName(),
                Level.FINER.getName(),
                Level.FINE.getName(),
                Level.CONFIG.getName(),
                Level.INFO.getName(),
                Level.WARNING.getName(),
                Level.SEVERE.getName(),
                Level.OFF.getName());
    }

    @Override
    public void setLogLevel(String category, String level) {
        if (LoggerInfo.ROOT_NAME.equals(category)) {
            category = "";
        }

        Logger log = LogManager.getLogManager().getLogger(category);
        if (level == null || "unset".equals(level) || "null".equals(level)) {
            if (log != null) {
                log.setLevel(null);
            }
        } else {
            if (log == null) {
                log = Logger.getLogger(category); // create it
            }
            log.setLevel(Level.parse(level));
        }
    }

//    @Override
//    public Collection<LoggerInfo> getAllLoggers() {
//        LogManager manager = LogManager.getLogManager();
//
//        Logger root = manager.getLogger("");
//        Map<String, LoggerInfo> map = new HashMap<String, LoggerInfo>();
//        Enumeration<String> names = manager.getLoggerNames();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            Logger logger = Logger.getLogger(name);
//            if (logger == root) {
//                continue;
//            }
//            map.put(name, new JulInfo(name, logger));
//
//            while (true) {
//                int dot = name.lastIndexOf(".");
//                if (dot < 0)
//                    break;
//                name = name.substring(0, dot);
//                if (!map.containsKey(name)) {
//                    map.put(name, new JulInfo(name, null));
//                }
//            }
//        }
//        map.put(LoggerInfo.ROOT_NAME, new JulInfo(LoggerInfo.ROOT_NAME, root));
//        return new TreeSet<LoggerInfo>(map.values());
//    }

    @Override
    public Set<Node> getLevels(String s) {
        return null;
    }
}