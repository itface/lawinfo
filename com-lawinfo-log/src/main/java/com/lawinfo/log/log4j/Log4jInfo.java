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


import com.lawinfo.log.LoggerInfo;

public class Log4jInfo extends LoggerInfo {
    final org.apache.log4j.Logger logger;

    public Log4jInfo(String name, org.apache.log4j.Logger logger) {
        super(name);
        this.logger = logger;
    }

    @Override
    public String getLevel() {
        if (logger == null) {
            return "";
        }
        Object level = logger.getLevel();
        if (level == null) {
            return "";
        }
        return level.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSet() {
        return (logger != null && logger.getLevel() != null);
    }
}