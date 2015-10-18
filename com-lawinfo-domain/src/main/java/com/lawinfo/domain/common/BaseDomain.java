package com.lawinfo.domain.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class BaseDomain implements Serializable {
    protected long createtime;
    protected String createtimestr;
    protected long modifiedtime;
    protected String modifiedtimestr;
    protected String optuserid;
    protected String description;


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
    public void initBaseDomain() {
        Date date = new Date();
        long now = date.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowstr = sf.format(date);
        this.createtime = now;
        this.createtimestr = nowstr;
        this.modifiedtime = now;
        this.modifiedtimestr = nowstr;
    }
}
