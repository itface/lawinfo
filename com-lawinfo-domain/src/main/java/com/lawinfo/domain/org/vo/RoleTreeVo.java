package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class RoleTreeVo implements Serializable,Comparable {
    private Long id;
    private String text;
    private List<RoleTreeVo> nodes;

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

    public List<RoleTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<RoleTreeVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return this.id.compareTo(((RoleTreeVo) o).getId());
        }
        return 0;
    }
}
