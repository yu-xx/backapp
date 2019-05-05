package com.knowworld.backapp.model.domain;

import javax.persistence.*;

/**
 * 字典
 */
@Entity
@Table(name = "s_dict")
public class SDict {
    /**
     * 字典ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictId;
    /**
     * 字典编码
     */
    @Column(nullable = false)
    private String dictCode;
    /**
     * 字典名称
     */
    @Column(nullable = false)
    private String dictName;
    /**
     * 排序号
     */
    @Column(nullable = false)
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
