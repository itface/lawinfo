package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.DeptDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangrongtao on 15/10/14.
 */
//@Service
public class DeptServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

//    @Resource
    private DeptDao deptDao;
/*
    @Override
    public void initCache() throws Exception {
        try {
            List<Dept> deptList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(deptList)) {
                for (Dept dept : deptList) {
                    DeptUtils.add(dept);
                }
            }
        } catch (Exception e) {
            logger.error("initCache exception",e);
            throw e;
        }
    }

    @Override
    public List<Dept> findAll() throws Exception{
        List<Dept> list = null;
        try {
            list = DeptUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = deptDao.findAll();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Dept> findAllFromDb() throws Exception {
        List<Dept> list = null;
        try {
            list = deptDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(Dept dept)throws Exception {
        int effectrows = 0;
        try {
            if (dept!=null) {
                dept.initBaseDomain();
                effectrows = deptDao.save(dept);
                logger.info("save success,effectrows:"+effectrows+","+dept.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+dept==null?"null":dept.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Dept findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Dept dept = null;
        try {
            dept=deptDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return dept;
    }

    @Override
    public List<Dept> findList(DeptQuery deptQuery)throws Exception {
        logger.info("findList begin,deptQuery=" + deptQuery == null ? "null" : deptQuery.toLogString());
        List<Dept> list = null;
        try {
            list = deptDao.findList(deptQuery);
        } catch (Exception e) {
            logger.error("findList error,deptQuery=" + deptQuery==null?"null":deptQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Dept> findListByPage(DeptQuery deptQuery)throws Exception {
        logger.info("findListByPage begin,deptQuery=" + deptQuery==null?"null":deptQuery.toLogString());
        List<Dept> list = null;
        try {
            list = deptDao.findListByPage(deptQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,deptQuery=" + deptQuery==null?"null":deptQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(DeptQuery deptQuery)throws Exception {
        logger.info("count begin,deptQuery=" + deptQuery==null?"null":deptQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = deptDao.count(deptQuery);
        } catch (Exception e) {
            logger.error("count error,deptQuery=" + deptQuery==null?"null":deptQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = deptDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }

    @Override
    public int deleteByOrgid(long orgid) throws Exception {
        logger.info("deleteByOrgid begin,orgid=" + orgid);
        int effectrows = 0;
        try {
            effectrows = deptDao.deleteByOrgid(orgid);
        } catch (Exception e) {
            logger.error("deleteByOrgid error,orgid=" + orgid, e);
        }
        return effectrows;
    }*/
}
