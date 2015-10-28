package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class Role extends BaseDomain {
    private long id;
    @NotBlank
    @Length(max=100)
    private String name;


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

}
