package com.lawinfo.service.org.aop;

import com.lawinfo.domain.org.*;
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
    /*
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private OrgService orgInfoService;
    @Resource
    private RoleService roleService;
    @Resource
    private PrivilegeService privilegeService;

    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.save(..)) && args(org)",argNames= "org")
    public void saveOrginfo(Org org) {
        if (org !=null) {
            logger.info("saveOrginfo begin,orgname:"+ org.getName());
            try {
                orgInfoService.initCache();
            } catch (Exception e) {
                logger.error("saveOrginfo exception,orgname:"+ org.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteOrginfoById(long id) {
        logger.info("saveOrginfo begin,id:"+id);
        try {
            orgInfoService.initCache();
            deptService.initCache();
        } catch (Exception e) {
            logger.error("saveOrginfo deleteOrginfoById,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.DeptServiceImpl.save(..)) && args(dept)",argNames= "dept")
    public void saveDept(Dept dept) {
        if (dept!=null) {
            logger.info("saveDept begin,deptname:"+dept.getName());
            try {
                orgInfoService.initCache();
            } catch (Exception e) {
                logger.error("saveDept exception,deptname:"+dept.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.DeptServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteDeptById(long id) {
        logger.info("deleteDeptById begin,id:"+id);
        try {
            deptService.initCache();
        } catch (Exception e) {
            logger.error("deleteDeptById exception,id:"+id, e);
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.PrivilegeServiceImpl.save(..)) && args(privilege)",argNames= "privilege")
    public void savePrivilege(Privilege privilege) {
        if (privilege!=null) {
            logger.info("savePrivilege begin,privilege:"+privilege.getName());
            try {
                privilegeService.initCache();
            } catch (Exception e) {
                logger.error("savePrivilege exception,privilege:"+privilege.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.PrivilegeServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deletePrivilegeById(long id) {
        logger.info("deletePrivilegeById begin,id:"+id);
        try {
            privilegeService.initCache();
        } catch (Exception e) {
            logger.error("deletePrivilegeById exception,id:"+id, e);
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
    }@AfterReturning(value="execution(* com.lawinfo.service.org.impl.UserServiceImpl.save(..)) && args(user)",argNames= "user")
    public void saveUser(User user) {
        if (user!=null) {
            logger.info("saveUser begin,user:"+user.getName());
            try {
                userService.initCache();
            } catch (Exception e) {
                logger.error("saveUser exception,user:"+user.getName(), e);
            }
        }
    }
    @AfterReturning(value="execution(* com.lawinfo.service.org.impl.OrgServiceImpl.deleteById(..)) && args(id)",argNames= "id")
    public void deleteUserById(long id) {
        logger.info("deleteUserById begin,id:"+id);
        try {
            userService.initCache();
        } catch (Exception e) {
            logger.error("deleteUserById exception,id:"+id, e);
        }
    }*/
}
