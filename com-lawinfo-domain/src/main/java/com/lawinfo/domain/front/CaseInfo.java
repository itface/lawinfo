package com.lawinfo.domain.front;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/11/3.
 */
public class CaseInfo extends BaseDomain{
    private long id;
    /**
     * 案件所属银行
     * orginfo表里的orgid,orgtype为银行
     */
    @Min(1)
    private long bankid;
    @Length(max=100)
    private String bankname;
    private int status;
    /**
     * 目前所属阶段
     *//*
    @Min(1)
    private long casenodeid;
    @Length(max=100)
    private String casenodename;*/
    /**
     * 案件联络人
     */
    @Length(max=100)
    private String contact;
    /**
     * 联系方式
     */
    @Length(max=50)
    private String contactphone;
    /**
     * 债务人debtor信息
     */
    @Length(max=150)
    private String debtorinfo;
    /**
     * 债权人creditor是否与律师所关联（1是、0否)
     */
    @Min(0)
    @Max(1)
    private int iscreditorrelated;
    /**
     * 债务人财产状况
     */
    @Length(max=150)
    private String debtorpropertyinfo;
    /**
     * 债权本金
     */
    private double zqbj;
    /**
     * 债权到期日
     */
    private long zqdqr;
    @Length(max=20)
    private String zqdqrstr;
    /**
     * 担保guarantee方式
     */
    @Length(max=50)
    private String guaranteetype;
    /**
     *担保人guarantor信息
     */
    @Length(max=150)
    private String guarantorinfo;
    /**
     * 担保人是否与律所关联（是1、否0）
     */
    @Min(0)
    @Max(1)
    private int isguarantorrelated;
    /**
     * 担保人财产情况
     */
    @Length(max=150)
    private String guarantorpropertyinfo;
    /**
     * 抵押物pawn信息
     */
    @Length(max=150)
    private String pawninfo;
    /**
     * 抵押物pawn评估价值
     */
    private double pawnvalue;
    /**
     * 案件程序
     */
    @Length(max=150)
    private String caseprocedure;
    /**
     * 受理法院
     */
    @Length(max=150)
    private String court;
    /**
     * userid
     */
    private String sslawyerid;
    private String exelawyerid;
    /**
     * 法官
     */
    private String judge;
    /**
     * 案由
     */
    private String ay;
    /**
     * 律师费总额
     */
    private double totalprice;

    /**
     * 摘要，聚合关键字段，用于查询
     */
    private String summary;
    /**
     *
     */
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getCaseprocedure() {
        return caseprocedure;
    }

    public void setCaseprocedure(String caseprocedure) {
        this.caseprocedure = caseprocedure;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
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

    public String getZqdqrstr() {
        return zqdqrstr;
    }

    public void setZqdqrstr(String zqdqrstr) {
        this.zqdqrstr = zqdqrstr;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSslawyerid() {
        return sslawyerid;
    }

    public void setSslawyerid(String sslawyerid) {
        this.sslawyerid = sslawyerid;
    }

    public String getExelawyerid() {
        return exelawyerid;
    }

    public void setExelawyerid(String exelawyerid) {
        this.exelawyerid = exelawyerid;
    }

    public String getTitle(){
        StringBuilder sb = new StringBuilder();
        sb.append(bankname==null?"":bankname)
                .append("诉").
                append(debtorinfo==null?"":debtorinfo)
                .append(ay==null?"":ay);
        return sb.toString();
    }

    public void initSummary() {
        StringBuilder sb = new StringBuilder();
        if (bankname!=null&&bankname.trim().length()>0) {
            sb.append("[").append(bankname).append("]");
        }if (contact!=null&&contact.trim().length()>0) {
            sb.append("[").append(contact).append("]");
        }if (court!=null&&court.trim().length()>0) {
            sb.append("[").append(court).append("]");
        }
        this.summary = sb.toString();
    }
}
