package com.yhsl.ipproxypools.service.ipinfo;

import com.github.pagehelper.PageInfo;
import com.yhsl.ipproxypools.domain.IPInfo;

import java.util.List;
import java.util.Map;

public interface IpInfoService {
    void addIpInfo(List<IPInfo> ipList);

    void executeAll();

    void execute(String sourceId);

    List<IPInfo> randomQueryIPInfo(int size);

    void addOrUpDate(IPInfo info);

    PageInfo<IPInfo> getByPage(Map<String, Object> map, int pageSize, int pageNum);
}
