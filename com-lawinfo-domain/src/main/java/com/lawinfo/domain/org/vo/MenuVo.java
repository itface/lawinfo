package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class MenuVo implements Serializable,Comparable {
    private Long id;
    private String text;
    private Long parentmenuid;
    private List<MenuVo> nodes;

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

    public Long getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(Long parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public List<MenuVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return this.id.compareTo(((MenuVo) o).getId());
        }
        return 0;
    }
}
