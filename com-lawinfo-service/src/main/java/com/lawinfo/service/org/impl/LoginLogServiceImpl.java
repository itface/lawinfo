package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.LoginLogDao;
import com.lawinfo.domain.org.LoginLog;
import com.lawinfo.domain.org.query.LoginLogQuery;
import com.lawinfo.service.org.LoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    private static Logger logger = LoggerFactory.getLogger(LoginLogServiceImpl.class);

    @Resource
    private LoginLogDao loginLogDao;

    @Override
    public List<LoginLog> findAll() throws Exception{
        List<LoginLog> list = null;
        try {
            list = loginLogDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public int save(LoginLog loginLog)throws Exception {
        int effectrows = 0;
        try {
            if (loginLog!=null) {
                loginLog.initBaseDomain();
                effectrows = loginLogDao.save(loginLog);
                logger.info("save success,effectrows:" + effectrows + "," + loginLog.getUsername());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+loginLog==null?"null":loginLog.getUsername(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public LoginLog findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        LoginLog loginLog = null;
        try {
            loginLog=loginLogDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return loginLog;
    }

    @Override
    public List<LoginLog> findList(LoginLogQuery loginLogQuery)throws Exception {
        logger.info("findList begin,LoginLogQuery=" + loginLogQuery == null ? "null" : loginLogQuery.toLogString());
        List<LoginLog> list = null;
        try {
            list = loginLogDao.findList(loginLogQuery);
        } catch (Exception e) {
            logger.error("findList error,LoginLogQuery=" + loginLogQuery==null?"null":loginLogQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<LoginLog> findListByPage(LoginLogQuery loginLogQuery)throws Exception {
        logger.info("findListByPage begin,LoginLogQuery=" + loginLogQuery==null?"null":loginLogQuery.toLogString());
        List<LoginLog> list = null;
        try {
            list = loginLogDao.findListByPage(loginLogQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,LoginLogQuery=" + loginLogQuery==null?"null":loginLogQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(LoginLogQuery loginLogQuery)throws Exception {
        logger.info("count begin,LoginLogQuery=" + loginLogQuery==null?"null":loginLogQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = loginLogDao.count(loginLogQuery);
        } catch (Exception e) {
            logger.error("count error,LoginLogQuery=" + loginLogQuery==null?"null":loginLogQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = loginLogDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
