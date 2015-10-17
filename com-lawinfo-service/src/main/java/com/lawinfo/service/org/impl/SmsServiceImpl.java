package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.SmsDao;
import com.lawinfo.domain.org.Sms;
import com.lawinfo.domain.org.query.SmsQuery;
import com.lawinfo.service.org.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
@Service
public class SmsServiceImpl implements SmsService {
    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Resource
    private SmsDao smsDao;

    @Override
    public boolean SendSms(String phoneno) {
        return false;
    }

    @Override
    public List<Sms> findAll() throws Exception{
        List<Sms> list = null;
        try {
            list = smsDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public int save(Sms sms)throws Exception {
        int effectrows = 0;
        try {
            if (sms!=null) {
                effectrows = smsDao.save(sms);
                logger.info("save success,effectrows:"+effectrows+","+sms.getPhoneno());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+sms==null?"null":sms.getPhoneno(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Sms findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Sms sms = null;
        try {
            sms=smsDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return sms;
    }

    @Override
    public List<Sms> findList(SmsQuery smsQuery)throws Exception {
        logger.info("findList begin,SmsQuery=" + smsQuery == null ? "null" : smsQuery.toLogString());
        List<Sms> list = null;
        try {
            list = smsDao.findList(smsQuery);
        } catch (Exception e) {
            logger.error("findList error,SmsQuery=" + smsQuery==null?"null":smsQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Sms> findListByPage(SmsQuery smsQuery)throws Exception {
        logger.info("findListByPage begin,SmsQuery=" + smsQuery==null?"null":smsQuery.toLogString());
        List<Sms> list = null;
        try {
            list = smsDao.findListByPage(smsQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,SmsQuery=" + smsQuery==null?"null":smsQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(SmsQuery smsQuery)throws Exception {
        logger.info("count begin,SmsQuery=" + smsQuery==null?"null":smsQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = smsDao.count(smsQuery);
        } catch (Exception e) {
            logger.error("count error,SmsQuery=" + smsQuery==null?"null":smsQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = smsDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
