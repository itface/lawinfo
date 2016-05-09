package com.lawinfo.domain.sourcegenerator;

import java.io.Serializable;

/**
 * Created by wangrongtao on 16/3/16.
 */
public class FieldModel implements Serializable {
    private String type;
    private String id;
    private String name;
    private boolean queryAble;
    private boolean showInList;
    private boolean showInEditForm;
    private boolean showInReadForm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowInList() {
        return showInList;
    }

    public void setShowInList(boolean showInList) {
        this.showInList = showInList;
    }

    public boolean isShowInEditForm() {
        return showInEditForm;
    }

    public void setShowInEditForm(boolean showInEditForm) {
        this.showInEditForm = showInEditForm;
    }

    public boolean isShowInReadForm() {
        return showInReadForm;
    }

    public void setShowInReadForm(boolean showInReadForm) {
        this.showInReadForm = showInReadForm;
    }

    public boolean isQueryAble() {
        return queryAble;
    }

    public void setQueryAble(boolean queryAble) {
        this.queryAble = queryAble;
    }
}
