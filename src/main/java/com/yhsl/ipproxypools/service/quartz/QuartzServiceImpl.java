package com.yhsl.ipproxypools.service.quartz;


import com.yhsl.ipproxypools.config.quartz.QuartzManager;
import com.yhsl.ipproxypools.domain.QuartzJob;
import com.yhsl.ipproxypools.mapper.quartz.QuartzMapper;
import com.yhsl.ipproxypools.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {

    private static Logger log = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    protected QuartzMapper quartzMapper;

    @Autowired
    protected QuartzManager quartzManager;

    @Override
    @Transactional
    public void addQuartzJob(QuartzJob job) {
        job.setId(Utils.createUUID());
        job.setCreateTime(new Date());
        quartzMapper.add(job);
    }

    @Override
    @Transactional(readOnly = true)
    public void startQuartzJob() {
        log.info("QuartzService.startQuartzJob start....");
        List<QuartzJob> all = quartzMapper.getAll();
        if (all != null && all.size() != 0) {
            for (QuartzJob job : all) {
                quartzManager.addJob(job);
                log.info("startQuartzJob addJob jobName={}", job.getJobName());
            }
        }
        //启动所有任务
        quartzManager.startAllJob();
        log.info("QuartzService.startQuartzJob end....");
    }

    @Override
    @Transactional
    public void pauseJob(String jobId) {
        QuartzJob job = quartzMapper.getById(jobId);
        job.setStatus("0");
        job.setUpdateTime(new Date());
        quartzMapper.uopdateJob(job);
        quartzManager.pauseJob(job);
    }

    @Override
    @Transactional
    public void resumeJob(String jobId) {
        QuartzJob job = quartzMapper.getById(jobId);
        job.setStatus("1");
        job.setUpdateTime(new Date());
        quartzMapper.uopdateJob(job);
        quartzManager.resumeJob(job);
    }
}
