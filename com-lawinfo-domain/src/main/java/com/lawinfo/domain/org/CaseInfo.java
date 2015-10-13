package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseInfo extends BaseDomain {
    private long id;
    /**
     * orginfo表里的orgid,orgtype为银行
     */
    private long bankid;
    private String bankname;
    private long casenodeid;
    private String casenodename;
    private String contact;
    private String contactphone;
    private String zwrinfo;
    private int iszqrrelated;
    private String zwrpropertyinfo;
    private double zqbj;
    private long zqdqr;
    private String dbfs;
    private String dbrinfo;
    private int isdbrrelated;
    private String dbrpropertyinfo;
    private String dywxx;
    private double dywvalue;
    private String ajcx;
    private String slfy;
    /**
     * userid
     */
    private String lawyerid;
    private String lawyername;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBankid() {
        return bankid;
    }

    public void setBankid(long bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public long getCasenodeid() {
        return casenodeid;
    }

    public void setCasenodeid(long casenodeid) {
        this.casenodeid = casenodeid;
    }

    public String getCasenodename() {
        return casenodename;
    }

    public void setCasenodename(String casenodename) {
        this.casenodename = casenodename;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getZwrinfo() {
        return zwrinfo;
    }

    public void setZwrinfo(String zwrinfo) {
        this.zwrinfo = zwrinfo;
    }

    public int getIszqrrelated() {
        return iszqrrelated;
    }

    public void setIszqrrelated(int iszqrrelated) {
        this.iszqrrelated = iszqrrelated;
    }

    public String getZwrpropertyinfo() {
        return zwrpropertyinfo;
    }

    public void setZwrpropertyinfo(String zwrpropertyinfo) {
        this.zwrpropertyinfo = zwrpropertyinfo;
    }

    public double getZqbj() {
        return zqbj;
    }

    public void setZqbj(double zqbj) {
        this.zqbj = zqbj;
    }

    public long getZqdqr() {
        return zqdqr;
    }

    public void setZqdqr(long zqdqr) {
        this.zqdqr = zqdqr;
    }

    public String getDbfs() {
        return dbfs;
    }

    public void setDbfs(String dbfs) {
        this.dbfs = dbfs;
    }

    public String getDbrinfo() {
        return dbrinfo;
    }

    public void setDbrinfo(String dbrinfo) {
        this.dbrinfo = dbrinfo;
    }

    public int getIsdbrrelated() {
        return isdbrrelated;
    }

    public void setIsdbrrelated(int isdbrrelated) {
        this.isdbrrelated = isdbrrelated;
    }

    public String getDbrpropertyinfo() {
        return dbrpropertyinfo;
    }

    public void setDbrpropertyinfo(String dbrpropertyinfo) {
        this.dbrpropertyinfo = dbrpropertyinfo;
    }

    public String getDywxx() {
        return dywxx;
    }

    public void setDywxx(String dywxx) {
        this.dywxx = dywxx;
    }

    public double getDywvalue() {
        return dywvalue;
    }

    public void setDywvalue(double dywvalue) {
        this.dywvalue = dywvalue;
    }

    public String getAjcx() {
        return ajcx;
    }

    public void setAjcx(String ajcx) {
        this.ajcx = ajcx;
    }

    public String getSlfy() {
        return slfy;
    }

    public void setSlfy(String slfy) {
        this.slfy = slfy;
    }

    public String getLawyerid() {
        return lawyerid;
    }

    public void setLawyerid(String lawyerid) {
        this.lawyerid = lawyerid;
    }

    public String getLawyername() {
        return lawyername;
    }

    public void setLawyername(String lawyername) {
        this.lawyername = lawyername;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
