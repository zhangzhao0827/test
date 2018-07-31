package com.yhsl.ipproxypools.domain;

import java.util.Date;

/**
 * ip爬取配置
 */
public class IPSourceConf {

    //主键
    private String id;
    //列名称
    private String colName;
    //列对应字段
    private String colField;
    //列爬取借点
    private String colElements;
    //列编号
    private int colNum;
    //类属性
    private String colAttr;
    //是否有元素 0 无 1 有
    private int isChild;
    //列对应子元素key
    private String childElementKey;
    //类对应子元素属性
    private String childElementAttr;
    //来源
    private String sourceId;
    //创建时间
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColField() {
        return colField;
    }

    public void setColField(String colField) {
        this.colField = colField;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public String getColAttr() {
        return colAttr;
    }

    public void setColAttr(String colAttr) {
        this.colAttr = colAttr;
    }

    public int getIsChild() {
        return isChild;
    }

    public void setIsChild(int isChild) {
        this.isChild = isChild;
    }

    public String getChildElementKey() {
        return childElementKey;
    }

    public void setChildElementKey(String childElementKey) {
        this.childElementKey = childElementKey;
    }

    public String getChildElementAttr() {
        return childElementAttr;
    }

    public void setChildElementAttr(String childElementAttr) {
        this.childElementAttr = childElementAttr;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getColElements() {
        return colElements;
    }

    public void setColElements(String colElements) {
        this.colElements = colElements;
    }
}
