package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseInfo extends BaseDomain {
    private long id;
    /**
     * 案件所属银行
     * orginfo表里的orgid,orgtype为银行
     */
    private long bankid;
    private String bankname;
    /**
     * 目前所属阶段
     */
    private long casenodeid;

    private String casenodename;
    /**
     * 案件联络人
     */
    private String contact;
    /**
     * 联系方式
     */
    private String contactphone;
    /**
     * 债务人debtor信息
     */
    private String debtorinfo;
    /**
     * 债权人creditor是否与律师所关联（1是、0否)
     */
    private int iscreditorrelated;
    /**
     * 债务人财产状部
     */
    private String debtorpropertyinfo;
    /**
     * 债权本金
     */
    private double zqbj;
    /**
     * 债权到期日
     */
    private long zqdqr;
    /**
     * 担保guarantee方式
     */
    private String guaranteetype;
    /**
     *担保人guarantor信息
     */
    private String guarantorinfo;
    /**
     * 担保人是否与律所关联（是1、否0）
     */
    private int isguarantorrelated;
    /**
     * 担保人财产情况
     */
    private String guarantorpropertyinfo;
    /**
     * 抵押物pawn信息
     */
    private String pawninfo;
    /**
     * 抵押物pawn评估价值
     */
    private double pawnvalue;
    /**
     * 案件程序
     */
    private String procedure;
    /**
     * 受理法院
     */
    private String court;
    /**
     * userid
     */
    private String lawyerid;
    private String lawyername;
    /**
     * 律师费总额
     */
    private double totalprice;

    /**
     * 摘要，聚合关键字段，用于查询
     */
    private String summary;

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

    public String getDebtorinfo() {
        return debtorinfo;
    }

    public void setDebtorinfo(String debtorinfo) {
        this.debtorinfo = debtorinfo;
    }

    public int getIscreditorrelated() {
        return iscreditorrelated;
    }

    public void setIscreditorrelated(int iscreditorrelated) {
        this.iscreditorrelated = iscreditorrelated;
    }

    public String getDebtorpropertyinfo() {
        return debtorpropertyinfo;
    }

    public void setDebtorpropertyinfo(String debtorpropertyinfo) {
        this.debtorpropertyinfo = debtorpropertyinfo;
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

    public String getGuaranteetype() {
        return guaranteetype;
    }

    public void setGuaranteetype(String guaranteetype) {
        this.guaranteetype = guaranteetype;
    }

    public String getGuarantorinfo() {
        return guarantorinfo;
    }

    public void setGuarantorinfo(String guarantorinfo) {
        this.guarantorinfo = guarantorinfo;
    }

    public int getIsguarantorrelated() {
        return isguarantorrelated;
    }

    public void setIsguarantorrelated(int isguarantorrelated) {
        this.isguarantorrelated = isguarantorrelated;
    }

    public String getGuarantorpropertyinfo() {
        return guarantorpropertyinfo;
    }

    public void setGuarantorpropertyinfo(String guarantorpropertyinfo) {
        this.guarantorpropertyinfo = guarantorpropertyinfo;
    }

    public String getPawninfo() {
        return pawninfo;
    }

    public void setPawninfo(String pawninfo) {
        this.pawninfo = pawninfo;
    }

    public double getPawnvalue() {
        return pawnvalue;
    }

    public void setPawnvalue(double pawnvalue) {
        this.pawnvalue = pawnvalue;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
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

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
