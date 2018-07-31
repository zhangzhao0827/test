package com.yhsl.ipproxypools.service.ipsourceconf;

import com.yhsl.ipproxypools.domain.IPSourceConf;

import java.util.List;
import java.util.Map;

public interface IPSourceConfService {
    void addConf(String conf);
    Map<String, IPSourceConf> getConfBySource(Map<String,Object> map);
}
