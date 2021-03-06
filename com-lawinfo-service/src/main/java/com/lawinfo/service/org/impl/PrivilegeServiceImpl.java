package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.PrivilegeDao;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.RolePrivilege;
import com.lawinfo.domain.org.query.PrivilegeQuery;
import com.lawinfo.service.org.PrivilegeService;
import com.lawinfo.service.org.RolePrivilegeService;
import com.lawinfo.service.org.utils.PrivilegeUtils;
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
public class PrivilegeServiceImpl implements PrivilegeService {
    
    private static Logger logger = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    @Resource
    private PrivilegeDao privilegeDao;
    @Resource
    private RolePrivilegeService rolePrivilegeService;

    @Override
    public void initCache() throws Exception {
        try {
            List<Privilege> privilegeList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(privilegeList)) {
                for (Privilege privilege : privilegeList) {
                    PrivilegeUtils.add(privilege);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }

    @Override
    public List<Privilege> findAll() throws Exception{
        List<Privilege> list = null;
        try {
            list = PrivilegeUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = privilegeDao.findAll();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Privilege> findAllFromDb() throws Exception {
        List<Privilege> list = null;
        try {
            list = privilegeDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(Privilege privilege)throws Exception {
        int effectrows = 0;
        try {
            if (privilege!=null) {
                privilege.initBaseDomain();
                effectrows = privilegeDao.save(privilege);
                logger.info("save success,effectrows:"+effectrows+","+privilege.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+privilege==null?"null":privilege.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Privilege findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Privilege privilege = null;
        try {
            privilege=privilegeDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return privilege;
    }

    @Override
    public List<Privilege> findList(PrivilegeQuery privilegeQuery)throws Exception {
        logger.info("findList begin,privilegeQuery=" + privilegeQuery == null ? "null" : privilegeQuery.toLogString());
        List<Privilege> list = null;
        try {
            list = privilegeDao.findList(privilegeQuery);
        } catch (Exception e) {
            logger.error("findList error,privilegeQuery=" + privilegeQuery==null?"null":privilegeQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Privilege> findListByPage(PrivilegeQuery privilegeQuery)throws Exception {
        logger.info("findListByPage begin,privilegeQuery=" + privilegeQuery==null?"null":privilegeQuery.toLogString());
        List<Privilege> list = null;
        try {
            list = privilegeDao.findListByPage(privilegeQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,privilegeQuery=" + privilegeQuery==null?"null":privilegeQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(PrivilegeQuery privilegeQuery)throws Exception {
        logger.info("count begin,privilegeQuery=" + privilegeQuery==null?"null":privilegeQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = privilegeDao.count(privilegeQuery);
        } catch (Exception e) {
            logger.error("count error,privilegeQuery=" + privilegeQuery==null?"null":privilegeQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            Privilege privilege = privilegeDao.findById(id);
            if (privilege != null) {
                int privilegeid = privilege.getPrivilegeid();
                rolePrivilegeService.deleteByPrivilegeid(privilegeid);
                effectrows = privilegeDao.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
