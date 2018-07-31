package com.yhsl.ipproxypools.service.quartz;

import com.yhsl.ipproxypools.domain.QuartzJob;

public interface QuartzService {
    void addQuartzJob(QuartzJob job);

    void startQuartzJob();

    void pauseJob(String jobId);

    void resumeJob(String jobId);
}
