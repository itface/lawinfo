package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.RoleMenuDao;
import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleMenuQuery;
import com.lawinfo.service.org.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService{
    private static Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    @Resource
    private RoleMenuDao roleMenuDao;

    @Override
    public List<RoleMenu> findAll() throws Exception{
        List<RoleMenu> list = null;
        try {
            list = roleMenuDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(RoleMenu roleMenu)throws Exception {
        int effectrows = 0;
        try {
            if (roleMenu!=null) {
                effectrows = roleMenuDao.save(roleMenu);
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public RoleMenu findById(long id)throws Exception {
        RoleMenu roleMenu = null;
        try {
            roleMenu=roleMenuDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return roleMenu;
    }

    @Override
    public List<RoleMenu> findByRoleid(long roleid) throws Exception {
        return roleMenuDao.findByRoleid(roleid);
    }

    @Override
    public List<RoleMenu> findByMenuid(long menuid) throws Exception {
        return roleMenuDao.findByMenuid(menuid);
    }

    @Override
    public List<RoleMenu> findList(RoleMenuQuery roleMenuQuery)throws Exception {
        logger.info("findList begin,RoleMenuQuery=" + roleMenuQuery == null ? "null" : roleMenuQuery.toLogString());
        List<RoleMenu> list = null;
        try {
            list = roleMenuDao.findList(roleMenuQuery);
        } catch (Exception e) {
            logger.error("findList error,RoleMenuQuery=" + roleMenuQuery==null?"null":roleMenuQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<RoleMenu> findListByPage(RoleMenuQuery roleMenuQuery)throws Exception {
        logger.info("findListByPage begin,RoleMenuQuery=" + roleMenuQuery==null?"null":roleMenuQuery.toLogString());
        List<RoleMenu> list = null;
        try {
            list = roleMenuDao.findListByPage(roleMenuQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,RoleMenuQuery=" + roleMenuQuery==null?"null":roleMenuQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(RoleMenuQuery roleMenuQuery)throws Exception {
        logger.info("count begin,RoleMenuQuery=" + roleMenuQuery==null?"null":roleMenuQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = roleMenuDao.count(roleMenuQuery);
        } catch (Exception e) {
            logger.error("count error,RoleMenuQuery=" + roleMenuQuery==null?"null":roleMenuQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = roleMenuDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByMenuid(long menuid) throws Exception {
        logger.info("deleteByMenuid begin,menuid=" + menuid);
        int effectrows = 0;
        try {
            effectrows = roleMenuDao.deleteByMenuid(menuid);
        } catch (Exception e) {
            logger.error("deleteByMenuid error,menuid=" + menuid, e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByRoleid(long roleid) throws Exception {
        logger.info("deleteByRoleid begin,roleid=" + roleid);
        int effectrows = 0;
        try {
            effectrows = roleMenuDao.deleteByRoleid(roleid);
        } catch (Exception e) {
            logger.error("deleteByRoleid error,roleid=" + roleid, e);
        }
        return effectrows;
    }
}
