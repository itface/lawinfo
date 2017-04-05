package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.UserDao;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.OrgQuery;
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
            UserUtils.init();
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
            list = UserUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = findAllFromDb();
            }
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
            user = UserUtils.findByUserid(userid);
            if (user==null) {
                user = userDao.findByUserid(userid);
            }
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
    public User findById(long id) throws Exception {
        logger.info("findById begin,id:"+id);
        User user = null;
        try {
            user = UserUtils.findById(id);
            if (user == null) {
                user = userDao.findById(id);
            }
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public UserVo findUservoById(long id)throws Exception {
//        logger.info("findById begin,id:"+id);
        UserVo uservo = null;
        try {
            User user = UserUtils.findById(id);
            if (user==null) {
                user = userDao.findById(id);
            }
            if (user != null) {
                uservo = new UserVo();
                uservo.setId(user.getId());
                uservo.setLogintype(user.getLogintype());
                uservo.setName(user.getName());
                uservo.setOrgid(user.getOrgid());
                uservo.setUserid(user.getUserid());
                Org org = orgService.findById(user.getOrgid());
                if (org != null) {
                    uservo.setOrgname(org.getName());
                }
                List<UserRole> userRoles = userRoleService.findByUserid(user.getUserid());
                if (!CollectionUtils.isEmpty(userRoles)) {
                    StringBuilder sb = new StringBuilder();
                    for (UserRole userRole : userRoles) {
                        sb.append(userRole.getRoleid()).append(",");
                    }
                    uservo.setRoleids(sb.substring(0, sb.length() - 1));
                }
            }
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return uservo;
    }

    @Override
    public List<User> findList(UserQuery userQuery)throws Exception {
        List<User> list = null;
        try {
            list = userDao.findList(userQuery);
        } catch (Exception e) {
            logger.error("findList error,UserQuery=" + userQuery==null?"null":userQuery.toLogString(), e);
            throw e;
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
            throw e;
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
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            User user = findById(id);
            if (user != null) {
                String userid = user.getUserid();
                userRoleService.deleteByUserid(userid);
                effectrows = userDao.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }
    @Override
    @Transactional
    public int deleteByOrgid(long orgid)throws Exception {
        logger.info("deleteByOrgid begin,orgid=" + orgid);
        int effectrows = 0;
        try {
            List<User> list = null;
            list = UserUtils.findByOrgid(orgid);
            if (CollectionUtils.isEmpty(list)) {
                UserQuery userQuery = new UserQuery();
                userQuery.setOrgid(orgid);
                list = this.findList(userQuery);
            }
            if (!CollectionUtils.isEmpty(list)) {
                for (User user : list) {
                    deleteById(user.getId());
                }
            }
        } catch (Exception e) {
            logger.error("deleteByOrgid error,orgid=" + orgid, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updatebyUservo(UserVo userVo) throws Exception {
        try {
            if (userVo != null) {
                User user2 = this.findById(userVo.getId());
                if (user2!=null) {
                    User user = new User();
                    user.setId(userVo.getId());
                    user.setName(userVo.getName());
                    user.setOrgid(userVo.getOrgid());
                    user.setLogintype(userVo.getLogintype());
                    String roleids = userVo.getRoleids();
                    userRoleService.deleteByUserid(user2.getUserid());
                    if (!StringUtils.isEmpty(roleids)) {
                        String[] roleidArr = roleids.split(",");
                        for (int i = 0; i < roleidArr.length; i++) {
                            UserRole userRole = new UserRole();
                            userRole.setUserid(userVo.getUserid());
                            userRole.setRoleid(Long.parseLong(roleidArr[i]));
                            userRoleService.save(userRole);
                        }
                    }
                    return userDao.update(user);
                }
            }
        } catch (Exception e) {
            logger.error("saveByUservo error", e);
            throw e;
        }
        return 0;
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
                if (!StringUtils.isEmpty(roleids)) {
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
                List<User> users = null;
                users = UserUtils.findByOrgid(orgid);
                if (CollectionUtils.isEmpty(users)) {
                    UserQuery userQuery = new UserQuery();
                    userQuery.setOrgid(orgid);
                    users = this.findList(userQuery);
                }
                if (!CollectionUtils.isEmpty(users)) {
                    for (User user : users) {
                        OrgVo usernode = new OrgVo();
                        usernode.setId(user.getId());
                        usernode.setText(user.getName());
                        usernode.setUserid(user.getUserid());
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
                        if (orgVo1.getType()!=1) {
                            buildOrgVo(orgVo1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("buildOrgVo error", e);
            throw e;
        }
    }

    @Override
    public List<String> findAllSubordinate(String userid) throws Exception {
        List<String> list = new ArrayList<String>();
        User user = findByUserid(userid);
        if (user!=null) {
            UserUtils.findSubordinate(user.getOrgid(),list);
        }
        return list;
    }

    public List<OrgVo> findSubordinateTree(String userid)throws Exception {
        List<OrgVo> orgVoList = null;
        try {
            User user = this.findByUserid(userid);
            if (user != null) {
                long rootOrgid = user.getOrgid();
                List<OrgVo> list = orgService.findSubOrgTree(rootOrgid);
                if (!CollectionUtils.isEmpty(list)) {
                    orgVoList = new ArrayList<OrgVo>();
                    OrgVo root = new OrgVo();
                    root.setId(0l);
                    root.setParentorgid(-1l);
                    root.setText("全部");
                    root.setNodes(list);
                    for (OrgVo orgVo : list) {
                        buildOrgVo(orgVo);
                    }
                    orgVoList.add(root);
                }
            }
        } catch (Exception e) {
            logger.error("findSubordinateTree error", e);
            throw e;
        }
        return orgVoList;
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

    @Override
    public List<OrgVo> findCustomerTree() throws Exception {
        List<OrgVo> list = null;
        try {
            list = orgService.findCustomTree();
            if (!CollectionUtils.isEmpty(list)) {
                for (OrgVo orgVo : list) {
                    buildOrgVo(orgVo);
                }
            }
        } catch (Exception e) {
            logger.error("findCustomerTree error", e);
            throw e;
        }
        return list;
    }

    @Override
    public List<OrgVo> findLawyerTree() throws Exception {
        List<OrgVo> list = null;
        try {
            list = orgService.findLawyerTree();
            if (!CollectionUtils.isEmpty(list)) {
                for (OrgVo orgVo : list) {
                    buildOrgVo(orgVo);
                }
            }
        } catch (Exception e) {
            logger.error("findLawyerTree error", e);
            throw e;
        }
        return list;
    }

    @Override
    public int updateWechatopenid(User user) {
        return userDao.updateWechatopenid(user);
    }

    @Override
    public User findByWechatopenid(String wechatopenid) {
        User user = UserUtils.findByByWechatopenid(wechatopenid);
        if (user == null) {
            user = userDao.findByWechatopenid(wechatopenid);
        }
        return user;
    }

    @Override
    public List<UserRole> findManagerList() throws Exception {
        Role role = roleService.findByRoletag("lawyer_company_manager");
        if (role != null) {
            List<UserRole> userRoles = userRoleService.findByRoleid(role.getId());
            return userRoles;
        }
        return null;
    }
}
