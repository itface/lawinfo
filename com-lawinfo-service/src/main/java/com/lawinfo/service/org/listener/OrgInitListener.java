package com.lawinfo.service.org.listener;

import com.lawinfo.domain.org.*;
import com.lawinfo.service.org.*;
import com.lawinfo.service.org.utils.*;
import com.lawinfo.service.sys.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/20.
 */
@Service
public class OrgInitListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LoggerFactory.getLogger(OrgInitListener.class);
    @Resource
    private CacheService cacheService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            cacheService.initCache();
        } catch (Exception e) {
            logger.error("init orgs exception",e);
        }
    }
}
