package com.yhsl.ipproxypools.service.quartz;

import com.yhsl.ipproxypools.service.ipinfo.IpInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class QuartzTask {
    private Logger log = LoggerFactory.getLogger(QuartzTask.class);

    @Autowired
    public IpInfoService ipInfoService;

    public void addOrUpDateIPInfo() {
        log.info("addORUpDateIPInfo...start");
        try {
            ipInfoService.executeAll();
        } catch (Exception e) {
            log.info("QuartzTask.addORUpDateIPInfo Exception ", e);
        }
        log.info("addORUpDateIPInfo...end");
    }


}
