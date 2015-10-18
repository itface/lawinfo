package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.CaseInfoDao;
import com.lawinfo.domain.org.CaseInfo;
import com.lawinfo.domain.org.query.CaseInfoQuery;
import com.lawinfo.service.org.CaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class CaseInfoServiceImpl implements CaseInfoService {
    private static Logger logger = LoggerFactory.getLogger(CaseInfoServiceImpl.class);

    @Resource
    private CaseInfoDao caseInfoDao;

    @Override
    public List<CaseInfo> findAll() throws Exception{
        List<CaseInfo> list = null;
        try {
            list = caseInfoDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public int save(CaseInfo caseInfo)throws Exception {
        int effectrows = 0;
        try {
            if (caseInfo!=null) {
                caseInfo.initSummary();
                effectrows = caseInfoDao.save(caseInfo);
                logger.info("save success,effectrows:"+effectrows+",caseid:"+caseInfo.getId());
            }
        } catch (Exception e) {
            logger.error("findAll error,caseid:"+caseInfo.getId(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public CaseInfo findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        CaseInfo caseInfo = null;
        try {
            caseInfo=caseInfoDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return caseInfo;
    }

    @Override
    public List<CaseInfo> findList(CaseInfoQuery caseInfoQuery)throws Exception {
        logger.info("findList begin,CaseInfoQuery=" + caseInfoQuery == null ? "null" : caseInfoQuery.toLogString());
        List<CaseInfo> list = null;
        try {
            list = caseInfoDao.findList(caseInfoQuery);
        } catch (Exception e) {
            logger.error("findList error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<CaseInfo> findListByPage(CaseInfoQuery caseInfoQuery)throws Exception {
        logger.info("findListByPage begin,caseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString());
        List<CaseInfo> list = null;
        try {
            list = caseInfoDao.findListByPage(caseInfoQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,caseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(CaseInfoQuery caseInfoQuery)throws Exception {
        logger.info("count begin,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = caseInfoDao.count(caseInfoQuery);
        } catch (Exception e) {
            logger.error("count error,CaseInfoQuery=" + caseInfoQuery==null?"null":caseInfoQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = caseInfoDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
