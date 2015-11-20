package com.lawinfo.domain.front.charts;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/20.
 */
public class EchartsSerie {
    private String name;
    private String type;
    private List<String> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
