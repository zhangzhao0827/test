package com.yhsl.ipproxypools.contorller;

import com.yhsl.ipproxypools.config.BaseController;
import com.yhsl.ipproxypools.domain.IPInfo;
import com.yhsl.ipproxypools.domain.IPSource;
import com.yhsl.ipproxypools.service.ipinfo.IpInfoService;
import com.yhsl.ipproxypools.service.ipsource.IpSourceService;
import com.yhsl.ipproxypools.service.ipsourceconf.IPSourceConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ipProxy")
public class IpContorller extends BaseController {

    @Autowired
    protected IpSourceService ipSourceService;
    @Autowired
    protected IPSourceConfService IPSourceConfService;
    @Autowired
    protected IpInfoService ipInfoService;

    @RequestMapping("/addSource")
    public Object addSource(IPSource iPSource) {
        try {
            ipSourceService.add(iPSource);
            LOG.info("addSource success");
            return success("add success");
        } catch (Exception e) {
            LOG.error("addSource fail", e);
            return fail(e);
        }
    }

    @RequestMapping("/addSourceConf")
    public Object addSourceConf(String conf) {
        try {
            IPSourceConfService.addConf(conf);
            LOG.info("addSource success");
            return success("add success");
        } catch (Exception e) {
            LOG.error("addSource fail", e);
            return fail(e);
        }
    }

    @RequestMapping("/getRandomIp")
    public Object getRandomIp(int size) {
        try {
            List<IPInfo> ipInfos = ipInfoService.randomQueryIPInfo(size);
            return success(ipInfos);
        } catch (Exception e) {
            LOG.error("getRandomIP fail", e);
            return fail(e);
        }
    }

    @RequestMapping("/getIpByPage")
    public Object getIpByPage(@RequestParam Map<String, Object> map, int pageSize, int pageNum) {
        try {
            return success(ipInfoService.getByPage(map, pageSize, pageNum));
        } catch (Exception e) {
            LOG.error("getIpByPage fail", e);
            return fail(e);
        }
    }

    @RequestMapping("/execute")
    public Object execute(String id) {
        try {
            ipInfoService.execute(id);
            return success("execute success");
        } catch (Exception e) {
            LOG.error("getIpByPage fail", e);
            return fail(e);
        }
    }

}
