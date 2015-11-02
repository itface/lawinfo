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
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.save(..)) && args(org)",argNames= "org")
    public void saveOrginfo(Org org) {
        if (org !=null) {
            logger.info("saveOrginfo begin,orgname:"+ org.getName());
            try {
                orgService.initCache();
            } catch (Exception e) {
                logger.error("saveOrginfo exception,orgname:"+ org.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteOrginfoById(long id) {
        logger.info("saveOrginfo begin,id:"+id);
        try {
            orgService.initCache();
        } catch (Exception e) {
            logger.error("saveOrginfo deleteOrginfoById,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.MenuServiceImpl.save(..)) && args(menu)",argNames= "menu")
    public void saveMenu(Menu menu) {
        if (menu!=null) {
            logger.info("saveMenu begin,menuname:"+menu.getName());
            try {
                menuService.initCache();
            } catch (Exception e) {
                logger.error("saveMenu exception,menuname:"+menu.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.MenuServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteMenuById(long id) {
        logger.info("deleteMenuById begin,id:"+id);
        try {
            menuService.initCache();
        } catch (Exception e) {
            logger.error("deleteMenuById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.ActionServiceImpl.save(..)) && args(action)",argNames= "action")
    public void saveAction(Action action) {
        if (action!=null) {
            logger.info("saveAction begin,action:"+action.getName());
            try {
                actionService.initCache();
            } catch (Exception e) {
                logger.error("saveAction exception,action:"+action.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.ActionServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteActionById(long id) {
        logger.info("deleteActionById begin,id:"+id);
        try {
            actionService.initCache();
        } catch (Exception e) {
            logger.error("deleteActionById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleServiceImpl.save(..)) && args(role)",argNames= "role")
    public void saveRole(Role role) {
        if (role!=null) {
            logger.info("saveRole begin,role:"+role.getName());
            try {
                roleService.initCache();
            } catch (Exception e) {
                logger.error("saveRole exception,role:"+role.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteRoleById(long id) {
        logger.info("deleteRoleById begin,id:"+id);
        try {
            roleService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleById exception,id:"+id, e);
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
    public void deleteUserById() {
        logger.info("deleteUserById begin");
        try {
            userService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserById exception", e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.save(..)) && args(userRole)",argNames= "userRole")
     public void saveUserRole(UserRole userRole) {
        if (userRole!=null) {
            try {
                userRoleService.initCache();
            } catch (Exception e) {
                logger.error("saveUser exception", e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteUserRoleById(long id) {
        logger.info("deleteUserRoleById begin,id:"+id);
        try {
            userRoleService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserRoleById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.deleteByRoleid(..)) && args(roleid)",argNames= "roleid")
    public void deleteUserRoleByRoleId(long roleid) {
        logger.info("deleteUserRoleByRoleId begin,roleid:"+roleid);
        try {
            userRoleService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserRoleByRoleId exception,roleid:"+roleid, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserRoleServiceImpl.deleteByUserid(..)) && args(userid)",argNames= "userid")
    public void deleteUserRoleByUserid(String userid) {
        logger.info("deleteUserRoleByUserid begin,userid:"+userid);
        try {
            userRoleService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserRoleByUserid exception,userid:"+userid, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.save(..)) && args(roleAction)",argNames= "roleAction")
     public void saveRoleAction(RoleAction roleAction) {
        if (roleAction!=null) {
            try {
                roleActionService.initCache();
            } catch (Exception e) {
                logger.error("saveUser exception", e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteRoleActionById(long id) {
        logger.info("deleteRoleActionById begin,id:"+id);
        try {
            roleActionService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleActionById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.deleteByRoleid(..)) && args(roleid)",argNames= "roleid")
    public void deleteRoleActionByRoleId(long roleid) {
        logger.info("deleteRoleActionByRoleId begin,roleid:"+roleid);
        try {
            roleActionService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleActionByRoleId exception,roleid:"+roleid, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleActionServiceImpl.deleteByActionid(..)) && args(actionid)",argNames= "actionid")
    public void deleteRoleActionByActionid(long actionid) {
        logger.info("deleteRoleActionByActionid begin,actionid:"+actionid);
        try {
            roleActionService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleActionByActionid exception,actionid:"+actionid, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.save(..)) && args(roleMenu)",argNames= "roleMenu")
     public void saveRoleMenu(RoleMenu roleMenu) {
        if (roleMenu!=null) {
            try {
                roleMenuService.initCache();
            } catch (Exception e) {
                logger.error("saveUser exception", e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteRoleMenuById(long id) {
        logger.info("deleteRoleMenuById begin,id:"+id);
        try {
            roleMenuService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleMenuById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.deleteByRoleid(..)) && args(roleid)",argNames= "roleid")
    public void deleteRoleMenuByRoleId(long roleid) {
        logger.info("deleteRoleMenuByRoleId begin,roleid:"+roleid);
        try {
            roleMenuService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleMenuByRoleId exception,roleid:"+roleid, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.RoleMenuServiceImpl.deleteByMenuid(..)) && args(menuid)",argNames= "menuid")
    public void deleteRoleMenuByActionid(long menuid) {
        logger.info("deleteRoleMenuByActionid begin,menuid:"+menuid);
        try {
            roleMenuService.initCache();
        } catch (Exception e) {
            logger.error("deleteRoleMenuByActionid exception,menuid:"+menuid, e);
        }
    }
}
