package com.lawinfo.service.org.listener;

import com.lawinfo.domain.org.*;
import com.lawinfo.service.org.*;
import com.lawinfo.service.org.utils.*;
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
/*
    private static Logger logger = LoggerFactory.getLogger(OrgInitListener.class);
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private OrgService orgInfoService;
    @Resource
    private RoleService roleService;
    @Resource
    private PrivilegeService privilegeService;*/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /*try {
            orgInfoService.initCache();;
            deptService.initCache();
            privilegeService.initCache();
            roleService.initCache();
            userService.initCache();
        } catch (Exception e) {
            logger.error("init orgs exception",e);
        }*/
    }
}
