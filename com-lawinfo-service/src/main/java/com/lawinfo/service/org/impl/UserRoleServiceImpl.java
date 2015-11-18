package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.UserRoleDao;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.UserRoleQuery;
import com.lawinfo.service.org.UserRoleService;
import com.lawinfo.service.org.utils.UserRoleUtils;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class UserRoleServiceImpl  implements UserRoleService{
    private static Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public void initCache() throws Exception {
        try {
            UserRoleUtils.init();
            List<UserRole> userRoles= this.findAll();
            if (!CollectionUtils.isEmpty(userRoles)) {
                for (UserRole userRole : userRoles) {
                    UserRoleUtils.add(userRole);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }
    @Override
    public List<UserRole> findAll() throws Exception{
        List<UserRole> list = null;
        try {
            list = UserRoleUtils.findAll();
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
    public List<UserRole> findAllFromDb() throws Exception {
        List<UserRole> list = null;
        try {
            list = userRoleDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(UserRole userRole)throws Exception {
        int effectrows = 0;
        try {
            if (userRole!=null) {
                effectrows = userRoleDao.save(userRole);
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return effectrows;
    }

    /*@Override
    public UserRole findById(long id)throws Exception {
        UserRole userRole = null;
        try {
            userRole=userRoleDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return userRole;
    }*/

    @Override
    public List<UserRole> findByRoleid(long roleid) throws Exception {
        try {
            List<UserRole> list = UserRoleUtils.findByRoleid(roleid);
            if (CollectionUtils.isEmpty(list)) {
                list = userRoleDao.findByRoleid(roleid);
            }
            return list;
        } catch (Exception e) {
            logger.error("findByRoleid error,roleid=" + roleid, e);
            throw e;
        }
    }

    @Override
    public List<UserRole> findByUserid(String userid) throws Exception {
        try {
            List<UserRole> list = UserRoleUtils.findByUserid(userid);
            if (CollectionUtils.isEmpty(list)) {
                list = userRoleDao.findByUserid(userid);
            }
            return list;
        } catch (Exception e) {
            logger.error("findByRoleid error,userid=" + userid, e);
            throw e;
        }
    }

    @Override
    public List<UserRole> findList(UserRoleQuery userRoleQuery)throws Exception {
        logger.info("findList begin,UserRoleQuery=" + userRoleQuery == null ? "null" : userRoleQuery.toLogString());
        List<UserRole> list = null;
        try {
            list = userRoleDao.findList(userRoleQuery);
        } catch (Exception e) {
            logger.error("findList error,UserRoleQuery=" + userRoleQuery==null?"null":userRoleQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<UserRole> findListByPage(UserRoleQuery userRoleQuery)throws Exception {
        logger.info("findListByPage begin,UserRoleQuery=" + userRoleQuery==null?"null":userRoleQuery.toLogString());
        List<UserRole> list = null;
        try {
            list = userRoleDao.findListByPage(userRoleQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,UserRoleQuery=" + userRoleQuery==null?"null":userRoleQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public int count(UserRoleQuery userRoleQuery)throws Exception {
        logger.info("count begin,UserRoleQuery=" + userRoleQuery==null?"null":userRoleQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = userRoleDao.count(userRoleQuery);
        } catch (Exception e) {
            logger.error("count error,UserRoleQuery=" + userRoleQuery==null?"null":userRoleQuery.toLogString(), e);
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
            effectrows = userRoleDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByUserid(String userid) throws Exception {
        logger.info("deleteByUserid begin,userid=" + userid);
        int effectrows = 0;
        try {
            effectrows = userRoleDao.deleteByUserid(userid);
        } catch (Exception e) {
            logger.error("deleteByUserid error,userid=" + userid, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByRoleid(long roleid) throws Exception {
        logger.info("deleteByRoleid begin,roleid=" + roleid);
        int effectrows = 0;
        try {
            effectrows = userRoleDao.deleteByRoleid(roleid);
        } catch (Exception e) {
            logger.error("deleteByRoleid error,roleid=" + roleid, e);
            throw e;
        }
        return effectrows;
    }
}
