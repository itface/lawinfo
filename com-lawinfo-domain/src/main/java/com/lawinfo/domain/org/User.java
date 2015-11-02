package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class User extends BaseDomain {
    private long id;
    @NotBlank
    @Length(max=100)
    private String name;
    /**
     * 手机号
     */
    @NotBlank
    @Length(max=100)
    private String userid;
    private String pwd;
    /**
     * 帐号状态,预留，1是正常，-1锁定
     */
    private int status;
    private long orgid;
    private long lastlogintime;
    private String lastlogintimestr;
    private int loginfailcount;
    /**
     * 0代表密码验证，1代码短信验证
     */
    private int logintype;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public int getLoginfailcount() {
        return loginfailcount;
    }

    public void setLoginfailcount(int loginfailcount) {
        this.loginfailcount = loginfailcount;
    }

    public long getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(long lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastlogintimestr() {
        return lastlogintimestr;
    }

    public void setLastlogintimestr(String lastlogintimestr) {
        this.lastlogintimestr = lastlogintimestr;
    }

    public int getLogintype() {
        return logintype;
    }

    public void setLogintype(int logintype) {
        this.logintype = logintype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userid.equals(user.userid);

    }

    @Override
    public int hashCode() {
        return userid.hashCode();
    }
}
