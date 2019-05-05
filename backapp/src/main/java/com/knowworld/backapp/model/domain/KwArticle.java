package com.knowworld.backapp.model.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章
 */
@Entity
@Table(name = "kw_article")
public class KwArticle {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    /**
     * 标题
     */
    @Column(nullable = false)
    private String title;
    /**
     * 摘要
     */
    @Column(nullable = false)
    private String summary;
    /**
     * 类型
     */
    @Column(nullable = false)
    private Integer type;
    /**
     * 文章来源
     */
    private Integer source;
    /**
     * 预览图
     */
    private String lowSource;
    /**
     * 内容
     */
    @Column(nullable = false)
    private String content;
    /**
     * 发布状态
     */
    @Column(nullable = false)
    private Integer status;
    /**
     * 有效标识1是2否
     */
    @Column(nullable = false)
    private Integer validFlag;
    /**
     * 创建人ID
     */
    @Column(nullable = false)
    private Long creId;
    /**
     * 创建人名称
     */
    @Column(nullable = false)
    private String creName;
    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Date creTime;
    /**
     * 操作人ID
     */
    private Long operId;
    /**
     * 操作人名称
     */
    private String operName;
    /**
     * 操作时间
     */
    private Date operTime;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getLowSource() {
        return lowSource;
    }

    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    public Long getCreId() {
        return creId;
    }

    public void setCreId(Long creId) {
        this.creId = creId;
    }

    public String getCreName() {
        return creName;
    }

    public void setCreName(String creName) {
        this.creName = creName;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
