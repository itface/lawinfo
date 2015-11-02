package com.lawinfo.service.org.aop;

import com.lawinfo.domain.org.*;
import com.lawinfo.domain.org.vo.UserVo;
import com.lawinfo.service.org.*;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 15/10/20.
 */
@Aspect
@Component
public class OrgInit {
    private static Logger logger = LoggerFactory.getLogger(OrgInit.class);

    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;
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
    @Resource
    private OrgService orgService;
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.OrgServiceImpl.insert*(..))")
    public void saveOrginfo() {
        logger.info("saveOrginfo begin:");
        try {
            orgService.initCache();
        } catch (Exception e) {
            logger.error("saveOrginfo exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.OrgServiceImpl.remove*(..))")
    public void deleteOrg() {
        logger.info("deleteOrg begin");
        try {
            orgService.initCache();
        } catch (Exception e) {
            logger.error("deleteOrg", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.MenuServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.MenuServiceImpl.insert*(..))")
    public void saveMenu() {
        logger.info("saveMenu begin,menuname:");
        try {
            menuService.initCache();
        } catch (Exception e) {
            logger.error("saveMenu exception,menuname:", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.MenuServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.MenuServiceImpl.remove*(..))")
    public void deleteMenu() {
        logger.info("deleteMenu begin");
        try {
            menuService.initCache();
        } catch (Exception e) {
            logger.error("deleteMenu exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.ActionServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.ActionServiceImpl.insert*(..))")
    public void saveAction() {
        logger.info("saveAction begin,action:");
        try {
            actionService.initCache();
        } catch (Exception e) {
            logger.error("saveAction exception,action:", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.ActionServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.ActionServiceImpl.remove*(..))")
    public void deleteAction() {
        logger.info("deleteAction begin");
        try {
            actionService.initCache();
        } catch (Exception e) {
            logger.error("deleteAction exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.RoleServiceImpl.insert*(..))")
    public void saveRole() {
        logger.info("saveRole begin");
        try {
            roleService.initCache();
        } catch (Exception e) {
            logger.error("saveRole exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.RoleServiceImpl.remove*(..))")
    public void deleteRoleById() {
        logger.info("deleteRoleById begin");
        try {
            roleService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleById exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.UserServiceImpl.insert*(..))")
     public void saveUser() {
        logger.info("saveUser begin");
        try {
            userService.initCache();
        } catch (Exception e) {
            logger.error("saveUser exception", e);
        }
    }
    /*@AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserServiceImpl.saveByUservo(..)) && args(userVo)",argNames= "userVo")
     public void saveUserVo(UserVo userVo) {
        if (userVo!=null) {
            try {
                userService.initCache();
            } catch (Exception e) {
                logger.error("saveUserVo exception", e);
            }
        }
    }*/
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.UserServiceImpl.remove*(..))")
    public void deleteUser() {
        logger.info("deleteUser begin");
        try {
            userService.initCache();
        } catch (Exception e) {
            logger.error("deleteUser exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.insert*(..))")
     public void saveUserRole() {
        try {
            userRoleService.initCache();
        } catch (Exception e) {
            logger.error("saveUser exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.remove*(..))")
    public void deleteUserRole() {
        logger.info("deleteUserRoleById begin");
        try {
            userRoleService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserRoleById exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.insert*(..))")
     public void saveRoleAction() {
        try {
            roleActionService.initCache();
        } catch (Exception e) {
            logger.error("saveUser exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.remove*(..))")
    public void deleteRoleAction() {
        logger.info("deleteRoleActionById begin");
        try {
            roleActionService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleActionById exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.save*(..))||execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.insert*(..))")
     public void saveRoleMenu() {
        try {
            roleMenuService.initCache();
        } catch (Exception e) {
            logger.error("saveUser exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.delete*(..))||execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.remove*(..))")
    public void deleteRoleMenu() {
        logger.info("deleteRoleMenuById begin");
        try {
            roleMenuService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleMenuById exception", e);
        }
    }
}
