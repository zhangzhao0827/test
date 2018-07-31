package com.yhsl.ipproxypools.config.quartz;

import com.yhsl.ipproxypools.service.quartz.QuartzTask;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        // 延时启动
        factory.setStartupDelay(10);
        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(springJobFactory);
        return factory;
    }

    /**
     * 创建调度器
     * @return Scheduler
     */
    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

    /**
     * job的执行类
     * @return QuartzTask
     */
    @Bean(name="quartzTask")
    public QuartzTask getQuartzTask(){
        return new QuartzTask();
    }
}
