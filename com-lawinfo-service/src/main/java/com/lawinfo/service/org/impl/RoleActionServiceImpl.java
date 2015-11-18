package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.RoleActionDao;
import com.lawinfo.domain.org.RoleAction;
import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.RoleActionQuery;
import com.lawinfo.service.org.RoleActionService;
import com.lawinfo.service.org.utils.RoleActionUtils;
import com.lawinfo.service.org.utils.UserRoleUtils;
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
public class RoleActionServiceImpl implements RoleActionService{
    private static Logger logger = LoggerFactory.getLogger(RoleActionServiceImpl.class);

    @Resource
    private RoleActionDao roleActionDao;

    @Override
    public void initCache() throws Exception {
        try {
            RoleActionUtils.init();
            List<RoleAction> roleActions= this.findAll();
            if (!CollectionUtils.isEmpty(roleActions)) {
                for (RoleAction roleAction : roleActions) {
                    RoleActionUtils.add(roleAction);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }
    @Override
    public List<RoleAction> findAll() throws Exception{
        List<RoleAction> list = null;
        try {
            list = RoleActionUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = roleActionDao.findAll();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<RoleAction> findAllFromDb() throws Exception {
        List<RoleAction> list = null;
        try {
            list = roleActionDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(RoleAction roleAction)throws Exception {
        int effectrows = 0;
        try {
            if (roleAction!=null) {
                effectrows = roleActionDao.save(roleAction);
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public List<RoleAction> findByRoleid(long roleid) throws Exception {
        try {
            List<RoleAction> list = RoleActionUtils.findByRoleid(roleid);
            if (CollectionUtils.isEmpty(list)) {
                list = roleActionDao.findByRoleid(roleid);
            }
            return list;
        } catch (Exception e) {
            logger.error("findByRoleid error,id=" + roleid, e);
            throw e;
        }
    }

    @Override
    public List<RoleAction> findByActionid(long actionid) throws Exception {
        try {
            List<RoleAction> list = RoleActionUtils.findByActionid(actionid);
            if (CollectionUtils.isEmpty(list)) {
                list = roleActionDao.findByActionid(actionid);
            }
            return list;
        } catch (Exception e) {
            logger.error("findByActionid error,actionid=" + actionid, e);
            throw e;
        }
    }

    @Override
    public List<RoleAction> findList(RoleActionQuery roleActionQuery)throws Exception {
        logger.info("findList begin,RoleActionQuery=" + roleActionQuery == null ? "null" : roleActionQuery.toLogString());
        List<RoleAction> list = null;
        try {
            list = roleActionDao.findList(roleActionQuery);
        } catch (Exception e) {
            logger.error("findList error,RoleActionQuery=" + roleActionQuery==null?"null":roleActionQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public List<RoleAction> findListByPage(RoleActionQuery roleActionQuery)throws Exception {
        logger.info("findListByPage begin,RoleActionQuery=" + roleActionQuery==null?"null":roleActionQuery.toLogString());
        List<RoleAction> list = null;
        try {
            list = roleActionDao.findListByPage(roleActionQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,RoleActionQuery=" + roleActionQuery==null?"null":roleActionQuery.toLogString(), e);
            throw e;
        }
        return list;
    }

    @Override
    public int count(RoleActionQuery roleActionQuery)throws Exception {
        logger.info("count begin,RoleActionQuery=" + roleActionQuery==null?"null":roleActionQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = roleActionDao.count(roleActionQuery);
        } catch (Exception e) {
            logger.error("count error,RoleActionQuery=" + roleActionQuery==null?"null":roleActionQuery.toLogString(), e);
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
            effectrows = roleActionDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByActionid(long actionid) throws Exception {
        logger.info("deleteByActionid begin,Actionid=" + actionid);
        int effectrows = 0;
        try {
            effectrows = roleActionDao.deleteByActionid(actionid);
        } catch (Exception e) {
            logger.error("deleteByActionid error,Actionid=" + actionid, e);
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
            effectrows = roleActionDao.deleteByRoleid(roleid);
        } catch (Exception e) {
            logger.error("deleteByRoleid error,roleid=" + roleid, e);
            throw e;
        }
        return effectrows;
    }
}
