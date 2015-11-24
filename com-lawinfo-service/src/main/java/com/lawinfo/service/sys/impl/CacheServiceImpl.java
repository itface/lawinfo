package com.lawinfo.service.sys.impl;

import com.lawinfo.service.org.*;
import com.lawinfo.service.sys.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 15/11/24.
 */
@Service
public class CacheServiceImpl implements CacheService {
    private static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;
    @Resource
    private OrgService orgService;
    @Resource
    private RoleService roleService;
    @Resource
    private ActionService actionService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleActionService roleActionService;
    @Resource
    private RoleMenuService roleMenuService;
    @Override
    public boolean initCache() {
        try {
            orgService.initCache();;
            menuService.initCache();
            actionService.initCache();
            roleService.initCache();
            userService.initCache();
            userRoleService.initCache();
            roleActionService.initCache();
            roleMenuService.initCache();
            return true;
        } catch (Exception e) {
            logger.error("initCache exception",e);
        }
        return false;
    }
}
