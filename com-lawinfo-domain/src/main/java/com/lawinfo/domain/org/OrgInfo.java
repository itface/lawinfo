package com.lawinfo.domain.org;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/12.
 */
public class OrgInfo implements Serializable {
    private long id;
    private String name;
    /**
     * 机构类型：1律所、2银行、3非银
     */
    private int type;
    private String desc;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
