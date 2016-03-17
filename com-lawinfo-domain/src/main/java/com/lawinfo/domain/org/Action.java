package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import com.lawinfo.domain.sourcegenerator.annotation.FormGlobalSetting;
import com.lawinfo.domain.sourcegenerator.annotation.InputType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wangrongtao on 15/10/26.
 */
@FormGlobalSetting(tableCaption="组织结构",baseurl="/login/org")
public class Action extends BaseDomain implements Comparable<Action>{

    @InputType(value = InputType.type.hidden,name="id")
    private long id;
    @NotBlank(message = "动作名称不能为空")
    @InputType(name="名称",showInList = true,showInEditForm=true,showInReadForm=true)
    private String name;
    /**
     * url，拦截器拦载url，判断权限
     */
    @InputType(name="动作key",showInList = true,showInEditForm=true,showInReadForm=true)
    private String actionkey;
    /**
     * 用于授权时归类，分组，没有特珠含义
     */
    @NotBlank(message = "动作tag不能为空")
    @InputType(value = InputType.type.date,name="动作tag",showInEditForm=true)
    private String tag;

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

    public String getActionkey() {
        return actionkey;
    }

    public void setActionkey(String actionkey) {
        this.actionkey = actionkey;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int compareTo(Action o) {
        if (o != null) {
            return id > o.getId()?1:-1;
        }
        return 0;
    }
}
