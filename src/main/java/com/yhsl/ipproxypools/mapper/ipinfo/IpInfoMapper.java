package com.yhsl.ipproxypools.mapper.ipinfo;

import com.yhsl.ipproxypools.domain.IPInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpInfoMapper {

    void batchAdd(List<IPInfo> list);

    List<IPInfo> randomQueryIPInfo(List<Integer> list);

    int getToatl();

    IPInfo getOne(Map<String, Object> map);

    void addOne(IPInfo info);

    void update(IPInfo info);

    List<IPInfo> getBy(Map<String, Object> map);
}
