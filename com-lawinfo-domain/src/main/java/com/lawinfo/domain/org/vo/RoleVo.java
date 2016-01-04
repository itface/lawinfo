package com.lawinfo.domain.org.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/27.
 */
public class RoleVo implements Serializable,Comparable {
    private Long id;
    private String name;
    private String menuname;
    private String actionname;
    private String roletag;
    private String menuids;
    private String actionids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getRoletag() {
        return roletag;
    }

    public void setRoletag(String roletag) {
        this.roletag = roletag;
    }

    public String getMenuids() {
        return menuids;
    }

    public void setMenuids(String menuids) {
        this.menuids = menuids;
    }

    public String getActionids() {
        return actionids;
    }

    public void setActionids(String actionids) {
        this.actionids = actionids;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return (this.id).compareTo(((RoleVo) o).getId());
        }
        return 0;
    }
}
