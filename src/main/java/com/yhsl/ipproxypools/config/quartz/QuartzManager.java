package com.yhsl.ipproxypools.config.quartz;

import com.yhsl.ipproxypools.config.BusinessException;
import com.yhsl.ipproxypools.domain.QuartzJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理
 */
@Component
public class QuartzManager {

    private static Logger log = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    protected Scheduler scheduler;


    /**
     * 启动所有已添加到scheduler的任务
     */
    public void startAllJob() {
        log.info("QuartzManager.startAllJob start........");
        try {
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.error("startAllJob SchedulerException: startAllJob fail", e);
            throw new BusinessException("startAllJob SchedulerException: startAllJob fail");
        }
        log.info("QuartzManager.startAllJob end........");
    }

    /**
     * 启动定时任务
     *
     * @param job
     */
    public void addJob(QuartzJob job) {
        log.info("QuartzManager.addJob start........");
        if (job == null) {
            throw new BusinessException("addJob fail: QuartzJob is empty");
        }
        try {
            String jobName = job.getJobName();
            String jobGroup = job.getJobGroup();
            String cron = job.getCron();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(JobExecute.class)
                    .withIdentity(jobName, jobGroup)
                    .usingJobData("classPath", job.getClassPath())
                    .usingJobData("methodName", job.getMethodName())
                    .build();
            //创建触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("addJob SchedulerException: addJob fail", e);
            throw new BusinessException("addJob SchedulerException: addJob fail");
        }
        log.info("QuartzManager.addJob end........");
    }

    /**
     * 暂停任务
     *
     * @param job
     */
    public void pauseJob(QuartzJob job) {
        log.info("QuartzManager.pauseJob start........");
        if (job == null) {
            throw new BusinessException("pauseJob fail: QuartzJob is empty");
        }
        try {
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("pauseJob SchedulerException: pauseJob fail", e);
            throw new BusinessException("pauseJob SchedulerException: pauseJob fail");
        }
        log.info("QuartzManager.pauseJob end........");
    }

    /**
     * 恢复任务
     *
     * @param job
     */
    public void resumeJob(QuartzJob job) {
        log.info("QuartzManager.resumeJob start........");
        if (job == null) {
            throw new BusinessException("resumeJob fail: QuartzJob is empty");
        }
        try {
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("resumeJob SchedulerException: resumeJob fail", e);
            throw new BusinessException("resumeJob SchedulerException: create fail");
        }
        log.info("QuartzManager.resumeJob end........");
    }
}
