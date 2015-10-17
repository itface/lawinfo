package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class Privilege extends BaseDomain {
    private long id;
    /**
     * 1、诉讼2、执行3、创建案建权限
     */
    @Min(1)
    private int privilegeid;

    @NotBlank
    @Length(max=100)
    private String name;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(int privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
