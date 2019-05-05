package com.knowworld.backapp.common;

/**
 * 分页参数
 */
public class PageArg {
    private Integer page;
    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public PageArg(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }
}
