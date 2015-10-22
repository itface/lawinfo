package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Sms;
import com.lawinfo.domain.org.query.SmsQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface SmsDao {

    public List<Sms> findAll();

    public int save(Sms sms);

    public Sms findById(long id);

    public List<Sms> findList(SmsQuery smsQuery);

    public List<Sms> findListByPage(SmsQuery smsQuery);

    public int count(SmsQuery smsQuery);

    public int deleteById(long id);

    public int updateLoginfailcount(long id);
}
