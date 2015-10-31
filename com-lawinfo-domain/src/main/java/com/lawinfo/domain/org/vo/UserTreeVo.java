package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class UserTreeVo implements Serializable,Comparable {
    private Long id;
    private String text;
    private Long orgid;
    private List<UserTreeVo> nodes;

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

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public List<UserTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<UserTreeVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return this.id.compareTo(((UserTreeVo) o).getId());
        }
        return 0;
    }
}
