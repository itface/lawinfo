package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class ActionTreeVo implements Serializable,Comparable {
    private Long id;
    private String text;
    private Long parentactionid;
    private List<ActionTreeVo> nodes;

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

    public Long getParentactionid() {
        return parentactionid;
    }

    public void setParentactionid(Long parentactionid) {
        this.parentactionid = parentactionid;
    }

    public List<ActionTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<ActionTreeVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return this.id.compareTo(((ActionTreeVo) o).getId());
        }
        return 0;
    }
}
