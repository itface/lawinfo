package com.lawinfo.domain.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/12/24.
 */
public class EasyuiTree implements Serializable {
    private long id;
    private String text;
    private String state;
    private String iconCls;
    private long parentid;
    private boolean checked;
    private List<EasyuiTree> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public List<EasyuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiTree> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
