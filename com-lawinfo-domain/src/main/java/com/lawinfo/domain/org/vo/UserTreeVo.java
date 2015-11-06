package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class UserTreeVo implements Serializable,Comparable {
    private Long id;
    private String userid;
    private String text;
    private Long parentid;
    private int nodetype;
    private Set<UserTreeVo> nodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Set<UserTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(Set<UserTreeVo> nodes) {
        this.nodes = nodes;
    }

    public int getNodetype() {
        return nodetype;
    }

    public void setNodetype(int nodetype) {
        this.nodetype = nodetype;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return this.id.compareTo(((UserTreeVo) o).getId());
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTreeVo that = (UserTreeVo) o;

        return userid.equals(that.userid);

    }

    @Override
    public int hashCode() {
        return userid.hashCode();
    }
}
