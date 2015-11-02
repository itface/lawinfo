package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wangrongtao on 15/11/1.
 */
public class Sms extends BaseDomain {
    private long id;
    @NotBlank
    @Length(max=100)
    private String phoneno;
    @NotBlank
    @Length(max=50)
    private String pwd;
    private long expiretime;
    private String expiretimestr;
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
