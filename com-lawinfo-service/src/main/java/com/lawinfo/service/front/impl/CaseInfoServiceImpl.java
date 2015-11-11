package com.lawinfo.service.front.impl;

import com.lawinfo.dao.front.CaseInfoDao;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
@Service
public class CaseInfoServiceImpl implements CaseInfoService {
    private static Logger logger = LoggerFactory.getLogger(CaseInfoServiceImpl.class);

    @Resource
    private CaseInfoDao caseInfoDao;
    @Resource
    private CaseInfoUserService caseInfoUserService;

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
    @Transactional
    public int save(CaseInfo caseInfo)throws Exception {
        int effectrows = 0;
        try {
            if (caseInfo!=null) {
                caseInfo.initSummary();
                caseInfo.initBaseDomain();
                if (SysConstants.CASEINFO_TYPE_INIT==caseInfo.getCasetype()) {
                    caseInfo.setStatus(SysConstants.CASEINFO_STATUS_FINISHED);
                }
                effectrows = caseInfoDao.save(caseInfo);
                if (effectrows > 0) {
                    long caseinfoid = caseInfo.getId();
                     String contactids = caseInfo.getContactids();
                     String sslawyerids = caseInfo.getSslawyerids();
                    if (!StringUtils.isEmpty(contactids)) {
                        String[] contactidArr = contactids.split(",");
                        for (int i = 0; i < contactidArr.length; i++) {
                            String userid = contactidArr[i];
                            CaseInfoUser caseInfoUser = new CaseInfoUser();
                            caseInfoUser.setUserid(userid);
                            caseInfoUser.setUsertype(SysConstants.CASEINFO_CONTACT_TYPE);
                            caseInfoUser.setCaseinfoid(caseinfoid);
                            caseInfoUserService.save(caseInfoUser);
                        }
                    }
                    if (!StringUtils.isEmpty(sslawyerids)) {
                        String[] sslaweridArr = sslawyerids.split(",");
                        for (int i = 0; i < sslaweridArr.length; i++) {
                            String sslawyerid = sslaweridArr[i];
                            CaseInfoUser caseInfoUser = new CaseInfoUser();
                            caseInfoUser.setUserid(sslawyerid);
                            caseInfoUser.setUsertype(SysConstants.CASEINFO_SSLAWYER_TYPE);
                            caseInfoUser.setCaseinfoid(caseinfoid);
                            caseInfoUserService.save(caseInfoUser);
                        }
                    }
                }
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
            throw e;
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
            throw e;
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
            effectrows = caseInfoDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateStatus(long caseinfoid, int status) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setStatus(status);
            effectrows = caseInfoDao.updateStatus(caseInfo);
        } catch (Exception e) {
            logger.error("updateStatus error,id="+caseinfoid+",status:"+status, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateYstj(long caseinfoid, int ystj) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setYstj(ystj);
            effectrows = caseInfoDao.updateYstj(caseInfo);
        } catch (Exception e) {
            logger.error("updateYstj error,id="+caseinfoid+",ystj:"+ystj, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateEstj(long caseinfoid, int estj) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setEstj(estj);
            effectrows = caseInfoDao.updateEstj(caseInfo);
        } catch (Exception e) {
            logger.error("updateEstj error,id="+caseinfoid+",estj:"+estj, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updatePrePrice(long caseinfoid, double prePrice) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setPreprice(prePrice);
            effectrows = caseInfoDao.updatePrePrice(caseInfo);
        } catch (Exception e) {
            logger.error("updatePrePrice error,id="+caseinfoid+",prePrice:"+prePrice, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateSufPrice(long caseinfoid, double sufPrice) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setSufprice(sufPrice);
            effectrows = caseInfoDao.updateSufPrice(caseInfo);
        } catch (Exception e) {
            logger.error("updateSufPrice error,id="+caseinfoid+",sufPrice:"+sufPrice, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int updateExeLawyers(long caseinfoid, String exeLawyers, String exeLawyerids) throws Exception {
        int effectrows = 0;
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setId(caseinfoid);
            caseInfo.setExelawyers(exeLawyers);
            effectrows = caseInfoDao.updateStatus(caseInfo);
            if (!StringUtils.isEmpty(exeLawyerids)) {
                String[] exeLawyerIdArr = exeLawyerids.split(",");
                for (int i = 0; i < exeLawyerIdArr.length; i++) {
                    CaseInfoUser caseInfoUser = new CaseInfoUser();
                    caseInfoUser.setCaseinfoid(caseinfoid);
                    caseInfoUser.setUserid(exeLawyerIdArr[i]);
                    caseInfoUser.setUsertype(SysConstants.CASEINFO_EXELAWYER_TYPE);
                    caseInfoUserService.save(caseInfoUser);
                }
            }
        } catch (Exception e) {
            logger.error("updateExeLawyers error,id="+caseinfoid+",exeLawyerids:"+exeLawyerids, e);
            throw e;
        }
        return effectrows;
    }
}
