package com.lawinfo.domain.front;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

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
    private long caseorgid;
    @Length(max=100)
    private String caseorgname;
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
    private String contacts;
    /**
     * 联系方式
     */
    @Length(max=50)
    private String contactids;
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
    private Date zqdqrdate;
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
    private String sslawyers;
    private String sslawyerids;
    private String exelawyers;
    private String exelawyerids;
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
    /**
     * 0表示新建，1表示事后新建，没有过程
     */
    private int casetype;
    /**
     * 0代表未完成，9999代表完成，其它数字代表节点的id
     */
    private int status;
    /**
     * 前期律师费
     */
    private double preprice;
    /**
     * 后期律师费
     */
    private double sufprice;
    /**
     * 一审调解，1表示未调解，2表示调解
     */
    private int ystj;
    /**
     * 二审调解，1表示未调解，2表示调解
     */
    private int estj;
    /**
     * 二审调解，1表示不上诉，2表示上诉
     */
    private int sfss;
    /**
     * 诉论案件编号
     */
    private String ssajbh;
    /**
     * 执行安件编号
     */
    private String exeajbh;

    private double realtotalprice;
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

    public long getCaseorgid() {
        return caseorgid;
    }

    public void setCaseorgid(long caseorgid) {
        this.caseorgid = caseorgid;
    }

    public String getCaseorgname() {
        return caseorgname;
    }

    public void setCaseorgname(String caseorgname) {
        this.caseorgname = caseorgname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactids() {
        return contactids;
    }

    public void setContactids(String contactids) {
        this.contactids = contactids;
    }

    public String getSslawyers() {
        return sslawyers;
    }

    public void setSslawyers(String sslawyers) {
        this.sslawyers = sslawyers;
    }

    public String getSslawyerids() {
        return sslawyerids;
    }

    public void setSslawyerids(String sslawyerids) {
        this.sslawyerids = sslawyerids;
    }

    public String getExelawyers() {
        return exelawyers;
    }

    public void setExelawyers(String exelawyers) {
        this.exelawyers = exelawyers;
    }

    public String getExelawyerids() {
        return exelawyerids;
    }

    public void setExelawyerids(String exelawyerids) {
        this.exelawyerids = exelawyerids;
    }

    public int getCasetype() {
        return casetype;
    }

    public void setCasetype(int casetype) {
        this.casetype = casetype;
    }

    public double getPreprice() {
        return preprice;
    }

    public void setPreprice(double preprice) {
        this.preprice = preprice;
    }

    public double getSufprice() {
        return sufprice;
    }

    public void setSufprice(double sufprice) {
        this.sufprice = sufprice;
    }

    public int getYstj() {
        return ystj;
    }

    public void setYstj(int ystj) {
        this.ystj = ystj;
    }

    public int getEstj() {
        return estj;
    }

    public void setEstj(int estj) {
        this.estj = estj;
    }

    public String getSsajbh() {
        return ssajbh;
    }

    public void setSsajbh(String ssajbh) {
        this.ssajbh = ssajbh;
    }

    public String getExeajbh() {
        return exeajbh;
    }

    public Date getZqdqrdate() {
        return zqdqrdate;
    }

    public void setZqdqrdate(Date zqdqrdate) {
        this.zqdqrdate = zqdqrdate;
    }

    public void setExeajbh(String exeajbh) {
        this.exeajbh = exeajbh;
    }

    public double getRealtotalprice() {
        return realtotalprice;
    }

    public void setRealtotalprice(double realtotalprice) {
        this.realtotalprice = realtotalprice;
    }

    public int getSfss() {
        return sfss;
    }

    public void setSfss(int sfss) {
        this.sfss = sfss;
    }

    public String getTitle(){
        StringBuilder sb = new StringBuilder();
        sb.append(caseorgname==null?"":caseorgname)
                .append("诉").
                append(debtorinfo==null?"":debtorinfo)
                .append(ay==null?"":ay);
        return sb.toString();
    }
    public void init() {
        if (zqdqrdate!=null) {
            this.zqdqr = zqdqrdate.getTime();
        }
        if (casetype==1) {
            this.realtotalprice = this.totalprice;
        }
    }
    public void initSummary() {
        StringBuilder sb = new StringBuilder();
        if (caseorgname!=null&&caseorgname.trim().length()>0) {
            sb.append("[").append(caseorgname).append("]");
        }if (contacts!=null&&contacts.trim().length()>0) {
            sb.append("[").append(contactids).append("]");
        }if (court!=null&&court.trim().length()>0) {
            sb.append("[").append(court).append("]");
        }
        this.summary = sb.toString();
    }

}
