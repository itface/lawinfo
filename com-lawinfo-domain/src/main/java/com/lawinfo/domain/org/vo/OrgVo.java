package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class OrgVo implements Serializable,Comparable {
    private Long id;
    private String text;
    private Long parentorgid;
    private List<OrgVo> nodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(Long parentorgid) {
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
            return (this.id).compareTo(((OrgVo) o).getId());
        }
        return 0;
    }
}
