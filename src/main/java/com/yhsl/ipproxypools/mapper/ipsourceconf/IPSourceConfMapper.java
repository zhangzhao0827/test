package com.yhsl.ipproxypools.mapper.ipsourceconf;

import com.yhsl.ipproxypools.domain.IPSourceConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IPSourceConfMapper {
    void addConf(List<IPSourceConf> list);
    List<IPSourceConf> getConfBySource(Map<String,Object> map);
}
