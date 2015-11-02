package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.UserDao;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.UserQuery;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.domain.org.vo.UserTreeVo;
import com.lawinfo.domain.org.vo.UserVo;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.RoleService;
import com.lawinfo.service.org.UserRoleService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import com.lawinfo.service.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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
    @Resource
    private OrgService orgService;

    @Override
    public void initCache() throws Exception {
        try {
            List<User> userList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(userList)) {
                for (User user : userList) {
                    UserUtils.add(user);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
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
                            roleIds.append(userRole.getRoleid()).append(",");
                            Role role = roleService.findById(userRole.getRoleid());
                            if (role != null) {
                                roleNames.append(role.getName()).append(",");
                            }

                        }
                        userVo.setRolenames(roleNames.substring(0, roleNames.length() - 1));
                        userVo.setRoleids(roleIds.substring(0, roleIds.length() - 1));
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
    public User findByUserid(String userid) throws Exception {
        logger.info("findByUserid begin,userid:"+userid);
        User user = null;
        try {
            user=userDao.findByUserid(userid);
        } catch (Exception e) {
            logger.error("findByUserid error,userid=" + userid, e);
            throw e;
        }
        return user;
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
                user.setOrgid(userVo.getOrgid());
                user.setStatus(0);
                user.setLogintype(userVo.getLogintype());
                user.setPwd(StrUtils.MD5(userVo.getUserid()));
                user.initBaseDomain();
                String roleids = userVo.getRoleids();
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
            throw e;
        }
        return 0;
    }

    private void buildOrgVo(OrgVo orgVo)throws Exception{
        try {
            if (orgVo!=null) {
                long orgid = orgVo.getId();
                UserQuery userQuery = new UserQuery();
                userQuery.setOrgid(orgid);
                List<User> users = findList(userQuery);
                if (!CollectionUtils.isEmpty(users)) {
                    for (User user : users) {
                        OrgVo usernode = new OrgVo();
                        usernode.setId(user.getId());
                        usernode.setText(user.getName());
                        usernode.setParentorgid(orgid);
                        usernode.setIcon("glyphicon glyphicon-user");
                        usernode.setType(1);
                        if (orgVo.getNodes() == null) {
                            List<OrgVo> son = new ArrayList<OrgVo>();
                            son.add(usernode);
                            orgVo.setNodes(son);
                        } else {
                            orgVo.getNodes().add(usernode);
                        }
                    }
                    Collections.sort(orgVo.getNodes());
                }
                List<OrgVo> sons = orgVo.getNodes();
                if (!CollectionUtils.isEmpty(sons)) {
                    for (OrgVo orgVo1 : sons) {
                        buildOrgVo(orgVo1);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("buildOrgVo error", e);
            throw e;
        }
    }
    @Override
    public List<OrgVo> findUserTreeVo() throws Exception {
        List<OrgVo> orgTrees = null;
        try {
            orgTrees = orgService.findOrgTree();
            if (!CollectionUtils.isEmpty(orgTrees)) {
                for (OrgVo orgVo : orgTrees) {
                    buildOrgVo(orgVo);
                }
            }
        } catch (Exception e) {
            logger.error("findUserTreeVo error", e);
            throw e;
        }
        return orgTrees;
    }

    @Override
    public int updateLoginStatus(User user) throws Exception {
        try {
            return userDao.updateLoginStatus(user);
        } catch (Exception e) {
            logger.error("updateLoginStatus error", e);
            throw e;
        }
    }
}
