package com.yhsl.ipproxypools.mapper.ipsource;

import com.yhsl.ipproxypools.domain.IPSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpSourceMapper {
    void add(IPSource ipSource);
    List<IPSource> getAll();
    IPSource getById(String id);
}
