package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.OrgInfoDao;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.OrgInfoQuery;
import com.lawinfo.domain.org.query.OrgInfoQuery;
import com.lawinfo.service.org.OrgInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Service
public class OrgInfoServiceImpl implements OrgInfoService {
    private static Logger logger = LoggerFactory.getLogger(OrgInfoServiceImpl.class);

    @Resource
    private OrgInfoDao orgInfoDao;
    @Resource
    private DeptServiceImpl deptService;

    @Override
    public List<OrgInfo> findAll() throws Exception{
        List<OrgInfo> list = null;
        try {
            list = orgInfoDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(OrgInfo orgInfo)throws Exception {
        int effectrows = 0;
        try {
            if (orgInfo!=null) {
                orgInfo.initBaseDomain();
                effectrows = orgInfoDao.save(orgInfo);
                logger.info("save success,effectrows:"+effectrows+","+orgInfo.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+orgInfo==null?"null":orgInfo.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public OrgInfo findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        OrgInfo orgInfo = null;
        try {
            orgInfo=orgInfoDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return orgInfo;
    }

    @Override
    public List<OrgInfo> findList(OrgInfoQuery orgInfoQuery)throws Exception {
        logger.info("findList begin,OrgInfoQuery=" + orgInfoQuery == null ? "null" : orgInfoQuery.toLogString());
        List<OrgInfo> list = null;
        try {
            list = orgInfoDao.findList(orgInfoQuery);
        } catch (Exception e) {
            logger.error("findList error,OrgInfoQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<OrgInfo> findListByPage(OrgInfoQuery orgInfoQuery)throws Exception {
        logger.info("findListByPage begin,OrgInfoQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString());
        List<OrgInfo> list = null;
        try {
            list = orgInfoDao.findListByPage(orgInfoQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,OrgInfoQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(OrgInfoQuery orgInfoQuery)throws Exception {
        logger.info("count begin,OrgInfoQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = orgInfoDao.count(orgInfoQuery);
        } catch (Exception e) {
            logger.error("count error,OrgInfoQuery=" + orgInfoQuery==null?"null":orgInfoQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            deptService.deleteByOrgid(id);
            effectrows = orgInfoDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
