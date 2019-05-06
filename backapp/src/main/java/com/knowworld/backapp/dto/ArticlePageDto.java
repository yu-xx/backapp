package com.knowworld.backapp.dto;

import com.knowworld.backapp.model.domain.KwArticle;

public class ArticlePageDto extends KwArticle {
    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 发布状态
     */
    private String statusName;
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
