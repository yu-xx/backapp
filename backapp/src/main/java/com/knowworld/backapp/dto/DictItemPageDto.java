package com.knowworld.backapp.dto;

import com.knowworld.backapp.model.domain.SDictItem;

public class DictItemPageDto  extends SDictItem {
    /**
     * 字典名称
     */
    private String dictName;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
