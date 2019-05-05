package com.knowworld.backapp.model.domain;

import javax.persistence.*;

/**
 * 字典项
 */
@Entity
@Table(name = "s_dict_item")
public class SDictItem {
    /**
     * 字典项ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictItemId;
    /**
     * 字典ID
     */
    private Long dictId;
    /**
     * 字典项编码
     */
    private Integer itemCode;
    /**
     * 字典项名称
     */
    private String itemName;
    /**
     * 排序号
     */
    private String orderNum;
    /**
     * 备注
     */
    private String remark;

    public Long getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(Long dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }
}
