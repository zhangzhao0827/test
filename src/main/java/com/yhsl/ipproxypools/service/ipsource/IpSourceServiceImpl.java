package com.yhsl.ipproxypools.service.ipsource;

import com.yhsl.ipproxypools.domain.IPSource;
import com.yhsl.ipproxypools.mapper.ipsource.IpSourceMapper;
import com.yhsl.ipproxypools.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class IpSourceServiceImpl implements IpSourceService {

    @Autowired
    protected IpSourceMapper ipSourceMapper;

    @Override
    @Transactional
    public void add(IPSource ipSource) {
        ipSource.setCreateTime(new Date());
        ipSource.setId(Utils.createUUID());
        ipSourceMapper.add(ipSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPSource> getAll() {
        return ipSourceMapper.getAll();
    }

    @Override
    public IPSource getById(String id) {
        return ipSourceMapper.getById(id);
    }
}
