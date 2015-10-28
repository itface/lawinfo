package com.lawinfo.service.org;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface SmsService {

    /**
     * 先校验手机号是否存在，然后判断当前有效的验证码是否超过3个，如果超过，则不能发送，如果没有，则可以再发
     * @param phoneno
     * @return
     */
    /*public boolean SendSms(String phoneno);
    public List<Sms> findAll()throws Exception;

    public int save(Sms sms)throws Exception;

    public Sms findById(long id)throws Exception;

    public List<Sms> findList(SmsQuery smsQuery)throws Exception;

    public List<Sms> findListByPage(SmsQuery smsQuery)throws Exception;

    public int count(SmsQuery smsQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int updateLoginfailcount(long id)throws Exception;*/
}
