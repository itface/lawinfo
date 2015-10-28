package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class OrgVo implements Serializable,Comparable {
    private long id;
    private String text;
    private long parentorgid;
    private List<OrgVo> nodes;

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

    public long getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(long parentorgid) {
        this.parentorgid = parentorgid;
    }

    public List<OrgVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<OrgVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return Long.valueOf(this.id).compareTo(((OrgVo) o).getId());
        }
        return 0;
    }
}
