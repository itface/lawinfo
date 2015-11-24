package com.lawinfo.service.front.impl;

import com.lawinfo.dao.front.CaseInfoDao;
import com.lawinfo.dao.front.CaseInfoUserDao;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
@Service
public class CaseInfoUserServiceImpl implements CaseInfoUserService {
    private static Logger logger = LoggerFactory.getLogger(CaseInfoUserServiceImpl.class);

    @Resource
    private CaseInfoUserDao caseInfoUserDao;

    @Override
    public List<CaseInfoUser> findAll() throws Exception{
        List<CaseInfoUser> list = null;
        try {
            list = caseInfoUserDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(CaseInfoUser caseInfoUser)throws Exception {
        int effectrows = 0;
        try {
            if (caseInfoUser!=null) {
                effectrows = caseInfoUserDao.save(caseInfoUser);
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public CaseInfoUser findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        CaseInfoUser caseInfoUser = null;
        try {
            caseInfoUser=caseInfoUserDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return caseInfoUser;
    }

    @Override
    public List<CaseInfoUser> findList(CaseInfoUserQuery caseInfoUserQuery)throws Exception {
        List<CaseInfoUser> list = null;
        try {
            list = caseInfoUserDao.findList(caseInfoUserQuery);
        } catch (Exception e) {
            logger.error("findList error", e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Long> findAllCaseinfoid(CaseInfoUserQuery caseInfoUserQuery) throws Exception {
        List<Long> list = null;
        try {
            list = caseInfoUserDao.findAllCaseinfoid(caseInfoUserQuery);
        } catch (Exception e) {
            logger.error("findList error", e);
            throw e;
        }
        return list;
    }

    @Override
    public List<CaseInfoUser> findListByPage(CaseInfoUserQuery caseInfoUserQuery)throws Exception {
        List<CaseInfoUser> list = null;
        try {
            list = caseInfoUserDao.findListByPage(caseInfoUserQuery);
        } catch (Exception e) {
            logger.error("findListByPage error", e);
            throw e;
        }
        return list;
    }


    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = caseInfoUserDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteByCaseinfoid(long caseinfoid) throws Exception {
        logger.info("deleteByCaseinfoid begin,caseinfoid=" + caseinfoid);
        int effectrows = 0;
        try {
            effectrows = caseInfoUserDao.deleteByCaseinfoid(caseinfoid);
        } catch (Exception e) {
            logger.error("deleteByCaseinfoid error,caseinfoid=" + caseinfoid, e);
            throw e;
        }
        return effectrows;
    }
}
