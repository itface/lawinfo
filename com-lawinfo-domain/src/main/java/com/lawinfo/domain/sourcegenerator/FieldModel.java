package com.lawinfo.domain.sourcegenerator;

import java.io.Serializable;

/**
 * Created by wangrongtao on 16/3/16.
 */
public class FieldModel implements Serializable {
    private String type;
    private String id;
    private String name;

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
}
