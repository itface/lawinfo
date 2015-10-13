package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class SmsLog extends BaseDomain {
    private long id;
    private String phoneno;
    private String pwd;
    private long expiretime;
    /**
     * 一个有效期内只能发一条验证码，保证一个时间点只有一个验证码有效
     */
    private String expiretimestr;
    /**
     * 登录时，先检查是否有该用户，如果无该用户直接返回失败。如果有，再检查该用户是否锁定，如果锁定,则不发验证码。
     * 如果没有锁定，验证规则：根据phoneno+系统时间小于等于expiretime条件查询smslog表（最多只可能有一条），如果无满足条件的记录，则直接返回验证失败。
     * 如果有满足条件的记录，说明有有效的短信验证码，再判断loginfailcount是否小于规定次数（不直接在sql中查询，提高效率）。
     * 如果前面条件都满足，再判断pwd是否相等，如果是则登录成功，如果否则验证失败，同时loginfailcount+1
     * （如果有多条有效的验证码，则多条都执行loginfailcount+1，更新方法要synchronized,有可能会出现loginfailcount大于最大失败次数，因为查询sql和判断是否能登录之间存在时间差）
     */
    private int loginfailcount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(long expiretime) {
        this.expiretime = expiretime;
    }

    public String getExpiretimestr() {
        return expiretimestr;
    }

    public void setExpiretimestr(String expiretimestr) {
        this.expiretimestr = expiretimestr;
    }

    public int getLoginfailcount() {
        return loginfailcount;
    }

    public void setLoginfailcount(int loginfailcount) {
        this.loginfailcount = loginfailcount;
    }
}
