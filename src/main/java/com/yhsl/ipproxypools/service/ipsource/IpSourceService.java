package com.yhsl.ipproxypools.service.ipsource;

import com.yhsl.ipproxypools.domain.IPSource;

import java.util.List;

public interface IpSourceService {
    void add(IPSource ipSource);

    List<IPSource> getAll();

    IPSource getById(String id);
}
