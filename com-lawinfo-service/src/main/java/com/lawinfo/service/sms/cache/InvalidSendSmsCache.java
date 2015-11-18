package com.lawinfo.service.sms.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangrongtao on 15/10/22.
 */
public class InvalidSendSmsCache {
    public static Cache<String, Long> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(60, TimeUnit.SECONDS)
            .build();
}
