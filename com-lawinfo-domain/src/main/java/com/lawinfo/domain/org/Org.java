package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/12.
 */
public class Org extends BaseDomain {

    private long id;

    @NotBlank
    @Length(max=100)
    private String name;
    private long parentorgid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(long parentorgid) {
        this.parentorgid = parentorgid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Org org = (Org) o;

        return id == org.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
