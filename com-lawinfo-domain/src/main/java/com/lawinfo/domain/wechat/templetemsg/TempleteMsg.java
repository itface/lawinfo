package com.lawinfo.domain.wechat.templetemsg;

import java.io.Serializable;

/**
 * Created by wangrongtao on 2016/10/22.
 */
public class TempleteMsg implements Serializable {

    private String toUserOpenId;
    private String templateId;
    private String url;
    private String first;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String remark;
    private long caseId;

    public String getToUserOpenId() {
        return toUserOpenId;
    }

    public void setToUserOpenId(String toUserOpenId) {
        this.toUserOpenId = toUserOpenId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }
}
