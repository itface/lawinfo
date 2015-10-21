package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.UserDao;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.UserQuery;
import com.lawinfo.service.org.UserRoleService;
import com.lawinfo.service.org.UserService;
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
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleService userRoleService;

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
            list = UserUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = userDao.findAll();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
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
}
