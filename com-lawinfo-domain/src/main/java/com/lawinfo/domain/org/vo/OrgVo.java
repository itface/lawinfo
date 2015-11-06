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
    private String icon;
    /**
     * 0代表机构，1代表人员
     */
    private int type;
    private String userid;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            int num = ((OrgVo) o).getType()-this.type;
            if (num==0) {
                return (this.id).compareTo(((OrgVo) o).getId());
            }
            return num;
        }
        return 0;
    }
}
