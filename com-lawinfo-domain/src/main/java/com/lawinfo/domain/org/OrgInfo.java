package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/12.
 */
public class OrgInfo extends BaseDomain {

    private long id;

    @NotBlank
    @Length(max=100)
    private String name;
    /**
     * 机构类型：1律所、2银行、3非银
     */
    @Min(1)
//    @Max(3)
    private int orgtype;

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

    public int getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(int orgtype) {
        this.orgtype = orgtype;
    }

}
