package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.UserDao;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.UserQuery;
import com.lawinfo.domain.org.vo.UserVo;
import com.lawinfo.service.org.RoleService;
import com.lawinfo.service.org.UserRoleService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class UserServiceImpl implements UserService{
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    @Override
    public void initCache() throws Exception {
        /*try {
            List<User> userList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(userList)) {
                for (User user : userList) {
                    UserUtils.add(user);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }*/
    }

    @Override
    public List<User> findAll() throws Exception{
        List<User> list = null;
        try {
            list = userDao.findAll();
            /*
            list = UserUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = userDao.findAll();
            }*/
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<UserVo> findAllUservo() throws Exception {
        try {
            List<User> list = this.findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<UserVo> userVoList = new ArrayList<UserVo>();
                for (User user : list) {
                    UserVo userVo = new UserVo();
                    userVo.setName(user.getName());
                    userVo.setUserid(user.getUserid());
                    userVo.setId(user.getId());
                    List<UserRole> userRoles = userRoleService.findByUserid(user.getUserid());
                    if (!CollectionUtils.isEmpty(userRoles)) {
                        StringBuilder roleIds = new StringBuilder();
                        StringBuilder roleNames = new StringBuilder();
                        for (UserRole userRole : userRoles) {
                            roleIds.append(userRole.getId()).append(",");
                            Role role = roleService.findById(userRole.getId());
                            if (role != null) {
                                roleNames.append(role.getName()).append(",");
                            }

                        }
                        userVo.setRolenames(roleNames.substring(0, roleNames.length() - 1));
                        userVo.setRoleIds(roleIds.substring(0, roleIds.length() - 1));
                    }
                    userVoList.add(userVo);
                }
                return userVoList;
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return null;
    }

    @Override
    public List<User> findAllFromDb() throws Exception {
        List<User> list = null;
        try {
            list = userDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(User user)throws Exception {
        int effectrows = 0;
        try {
            if (user!=null) {
                user.initBaseDomain();
                effectrows = userDao.save(user);
                logger.info("save success,effectrows:"+effectrows+","+user.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+user==null?"null":user.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public User findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        User user = null;
        try {
            user=userDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return user;
    }

    @Override
    public List<User> findList(UserQuery userQuery)throws Exception {
        List<User> list = null;
        try {
            list = userDao.findList(userQuery);
        } catch (Exception e) {
            logger.error("findList error,UserQuery=" + userQuery==null?"null":userQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<User> findListByPage(UserQuery userQuery)throws Exception {
        logger.info("findListByPage begin,UserQuery=" + userQuery==null?"null":userQuery.toLogString());
        List<User> list = null;
        try {
            list = userDao.findListByPage(userQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,UserQuery=" + userQuery==null?"null":userQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(UserQuery userQuery)throws Exception {
        logger.info("count begin,UserQuery=" + userQuery==null?"null":userQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = userDao.count(userQuery);
        } catch (Exception e) {
            logger.error("count error,UserQuery=" + userQuery==null?"null":userQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            User user = userDao.findById(id);
            if (user != null) {
                String userid = user.getUserid();
                userRoleService.deleteByUserid(userid);
                effectrows = userDao.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int saveByUservo(UserVo userVo) throws Exception {
        try {
            if (userVo != null) {
                User user = new User();
                user.setName(userVo.getName());
                user.setUserid(userVo.getUserid());
                String roleids = userVo.getRoleIds();
                if (!StringUtils.isEmpty(userVo)) {
                    String[] roleidArr = roleids.split(",");
                    for (int i = 0; i < roleidArr.length; i++) {
                        UserRole userRole = new UserRole();
                        userRole.setUserid(userVo.getUserid());
                        userRole.setRoleid(Long.parseLong(roleidArr[i]));
                        userRoleService.save(userRole);
                    }
                }
                return userDao.save(user);
            }
        } catch (Exception e) {
            logger.error("saveByUservo error", e);
        }
        return 0;
    }
}
