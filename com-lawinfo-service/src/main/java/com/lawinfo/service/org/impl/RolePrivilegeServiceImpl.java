package com.lawinfo.service.org.impl;

/**
 * Created by wangrongtao on 15/10/14.
 */
//@Service
public class RolePrivilegeServiceImpl{
    /*private static Logger logger = LoggerFactory.getLogger(RolePrivilegeServiceImpl.class);

    @Resource
    private RoleActionDao rolePrivilegeDao;

    @Override
    public List<RoleMenu> findAll() throws Exception{
        List<RoleMenu> list = null;
        try {
            list = rolePrivilegeDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(RoleMenu rolePrivilege)throws Exception {
        int effectrows = 0;
        try {
            if (rolePrivilege!=null) {
                effectrows = rolePrivilegeDao.save(rolePrivilege);
                logger.info("save success,effectrows:"+effectrows);
            }
        } catch (Exception e) {
            logger.error("findAll error,",e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public RoleMenu findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        RoleMenu rolePrivilege = null;
        try {
            rolePrivilege=rolePrivilegeDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return rolePrivilege;
    }

    @Override
    public List<RoleMenu> findList(RoleActionQuery rolePrivilegeQuery)throws Exception {
        List<RoleMenu> list = null;
        try {
            list = rolePrivilegeDao.findList(rolePrivilegeQuery);
        } catch (Exception e) {
            logger.error("findList error,RoleActionQuery=" + rolePrivilegeQuery==null?"null":rolePrivilegeQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<RoleMenu> findListByPage(RoleActionQuery rolePrivilegeQuery)throws Exception {
        List<RoleMenu> list = null;
        try {
            list = rolePrivilegeDao.findListByPage(rolePrivilegeQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,RoleActionQuery=" + rolePrivilegeQuery==null?"null":rolePrivilegeQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(RoleActionQuery rolePrivilegeQuery)throws Exception {
        int effectrows = 0;
        try {
            effectrows = rolePrivilegeDao.count(rolePrivilegeQuery);
        } catch (Exception e) {
            logger.error("count error,RoleActionQuery=" + rolePrivilegeQuery==null?"null":rolePrivilegeQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = rolePrivilegeDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
    @Override
    @Transactional
    public int deleteByPrivilegeid(int privilegeid) throws Exception {
        logger.info("deleteByPrivilegeid begin,privilegeid=" + privilegeid);
        int effectrows = 0;
        try {
            effectrows = rolePrivilegeDao.deleteByPrivilegeid(privilegeid);
        } catch (Exception e) {
            logger.error("deleteByPrivilegeid error,privilegeid=" + privilegeid, e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByRoleid(int roleid) throws Exception {
        logger.info("deleteByRoleid begin,roleid=" + roleid);
        int effectrows = 0;
        try {
            effectrows = rolePrivilegeDao.deleteByRoleid(roleid);
        } catch (Exception e) {
            logger.error("deleteByRoleid error,roleid=" + roleid, e);
        }
        return effectrows;
    }*/
}
