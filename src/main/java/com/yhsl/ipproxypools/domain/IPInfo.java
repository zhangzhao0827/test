package com.yhsl.ipproxypools.domain;

import java.util.Date;

/**
 * 代理ip详情
 */
public class IPInfo {

    //source_ip_端口
    private String id;
    //ip地址
    private String ipAddress;
    //端口
    private String ipPort;
    //http类型
    private HttpType httpType;
    //速度
    private String ipSpeed;
    //服务器所在地
    private String serverAddress;
    //验证时间
    private Date verificationTime;
    //来源
    private String sourceId;
    //创建时间
    private Date createTime;
    //状态 0不可用 1可用
    private int proxyStatus;
    //修改时间
    private Date updateTime;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public HttpType getHttpType() {
        return httpType;
    }

    public void setHttpType(HttpType httpType) {
        this.httpType = httpType;
    }

    public String getIpSpeed() {
        return ipSpeed;
    }

    public void setIpSpeed(String ipSpeed) {
        this.ipSpeed = ipSpeed;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getProxyStatus() {
        return proxyStatus;
    }

    public void setProxyStatus(int proxyStatus) {
        this.proxyStatus = proxyStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
