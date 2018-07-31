package com.yhsl.ipproxypools.domain;

import java.util.Date;

/**
 * 代理来源
 */
public class IPSource {
    private String id;
    //网站名称
    private String sourceName;
    //地址
    private String sourceUrl;
    //爬取页面dom节点
    private String pageElements;
    //最大页数
    private int maxPage;
    //创建时间
    private Date createTime;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPageElements() {
        return pageElements;
    }

    public void setPageElements(String pageElements) {
        this.pageElements = pageElements;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
