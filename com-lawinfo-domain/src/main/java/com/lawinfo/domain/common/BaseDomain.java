package com.lawinfo.domain.common;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class BaseDomain implements Serializable {
    private long createtime;
    private String createtimestr;
    private long modifiedtime;
    private String modifiedtimestr;
    private String optuserid;
    private String description;


    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getCreatetimestr() {
        return createtimestr;
    }

    public void setCreatetimestr(String createtimestr) {
        this.createtimestr = createtimestr;
    }

    public long getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public String getModifiedtimestr() {
        return modifiedtimestr;
    }

    public void setModifiedtimestr(String modifiedtimestr) {
        this.modifiedtimestr = modifiedtimestr;
    }

    public String getOptuserid() {
        return optuserid;
    }

    public void setOptuserid(String optuserid) {
        this.optuserid = optuserid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
