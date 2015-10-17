package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.DeptDao;
import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.service.org.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class DeptServiceImpl implements DeptService {
    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Resource
    private DeptDao deptDao;

    @Override
    public List<Dept> findAll() throws Exception{
        List<Dept> list = null;
        try {
            list = deptDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public int save(Dept dept)throws Exception {
        int effectrows = 0;
        try {
            if (dept!=null) {
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
}
