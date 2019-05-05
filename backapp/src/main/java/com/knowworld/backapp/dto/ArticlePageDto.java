package com.knowworld.backapp.dto;

import com.knowworld.backapp.model.domain.KwArticle;

public class ArticlePageDto extends KwArticle {
    /**
     * 类型名称
     */
    private String typeName;
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
