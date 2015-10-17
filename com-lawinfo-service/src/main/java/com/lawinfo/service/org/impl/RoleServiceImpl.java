package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.RoleDao;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.query.RoleQuery;
import com.lawinfo.service.org.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception{
        List<Role> list = null;
        try {
            list = roleDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public int save(Role role)throws Exception {
        int effectrows = 0;
        try {
            if (role!=null) {
                effectrows = roleDao.save(role);
                logger.info("save success,effectrows:"+effectrows+","+role.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+role==null?"null":role.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Role findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Role role = null;
        try {
            role=roleDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return role;
    }

    @Override
    public List<Role> findList(RoleQuery roleQuery)throws Exception {
        logger.info("findList begin,roleQuery=" + roleQuery == null ? "null" : roleQuery.toLogString());
        List<Role> list = null;
        try {
            list = roleDao.findList(roleQuery);
        } catch (Exception e) {
            logger.error("findList error,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Role> findListByPage(RoleQuery roleQuery)throws Exception {
        logger.info("findListByPage begin,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString());
        List<Role> list = null;
        try {
            list = roleDao.findListByPage(roleQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,RoleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(RoleQuery roleQuery)throws Exception {
        logger.info("count begin,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = roleDao.count(roleQuery);
        } catch (Exception e) {
            logger.error("count error,roleQuery=" + roleQuery==null?"null":roleQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = roleDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
