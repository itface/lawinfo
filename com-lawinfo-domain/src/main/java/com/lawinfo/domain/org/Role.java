package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class Role extends BaseDomain implements Comparable<Role>{
    private long id;
    @NotBlank
    @Length(max=100)
    private String name;
    private String roletag;


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

    public String getRoletag() {
        return roletag;
    }

    public void setRoletag(String roletag) {
        this.roletag = roletag;
    }
    @Override
    public int compareTo(Role o) {
        if (o != null) {
            return id > o.getId()?1:-1;
        }
        return 0;
    }
}
