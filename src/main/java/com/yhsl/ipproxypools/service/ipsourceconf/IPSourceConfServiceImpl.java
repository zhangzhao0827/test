package com.yhsl.ipproxypools.service.ipsourceconf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhsl.ipproxypools.domain.IPSourceConf;
import com.yhsl.ipproxypools.mapper.ipsourceconf.IPSourceConfMapper;
import com.yhsl.ipproxypools.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class IPSourceConfServiceImpl implements IPSourceConfService {

    @Autowired
    protected IPSourceConfMapper iPSourceConfMapper;

    @Override
    @Transactional
    public void addConf(String conf) {
        JSONArray confs = JSONArray.parseArray(conf);
        List<IPSourceConf> list = new ArrayList<IPSourceConf>();
        if (confs != null && confs.size() != 0) {
            for (int i = 0; i < confs.size(); i++) {
                JSONObject obj = confs.getJSONObject(i);
                IPSourceConf ipConf = new IPSourceConf();
                ipConf.setColName(obj.getString("colName"));
                ipConf.setColField(obj.getString("colField"));
                ipConf.setColNum(obj.getIntValue("colNum"));
                ipConf.setColAttr(obj.getString("colAttr"));
                ipConf.setIsChild(obj.getIntValue("isChild"));
                ipConf.setChildElementKey(obj.getString("childElementKey"));
                ipConf.setChildElementAttr(obj.getString("childElementAttr"));
                ipConf.setSourceId(obj.getString("sourceId"));
                ipConf.setColElements(obj.getString("colElements"));
                ipConf.setCreateTime(new Date());
                ipConf.setId(Utils.createUUID());
                list.add(ipConf);
            }
            if (list.size() != 0) {
                iPSourceConfMapper.addConf(list);
            }
        }
    }

    @Override
    public Map<String, IPSourceConf> getConfBySource(Map<String, Object> map) {
        Map<String, IPSourceConf> ipSourceConfMap = new HashMap<String, IPSourceConf>();
        List<IPSourceConf> confBySource = iPSourceConfMapper.getConfBySource(map);
        if (confBySource != null && confBySource.size() != 0) {
            for (IPSourceConf conf : confBySource) {
                ipSourceConfMap.put(conf.getColField(),conf);
            }
        }
        return ipSourceConfMap;
    }
}
