package com.lawinfo.domain.common;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class BaseQuery extends BaseDomain{
    private int startRow;
    private int endRow;
    private int pageSize;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
