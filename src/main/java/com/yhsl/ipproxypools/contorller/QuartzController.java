package com.yhsl.ipproxypools.contorller;

import com.yhsl.ipproxypools.config.BaseController;
import com.yhsl.ipproxypools.domain.QuartzJob;
import com.yhsl.ipproxypools.service.quartz.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzController extends BaseController {

    @Autowired
    protected QuartzService quartzService;

    /**
     * 暂停任务
     * @param jobId
     * @return Object
     */
    @RequestMapping("/pauseJob")
    public Object pauseJob(String jobId) {
        try {
            quartzService.pauseJob(jobId);
            return success("pauseJob success");
        } catch (Exception e) {
            LOG.error("pauseJob Exception", e);
            return fail(e);
        }
    }

    /**
     * 恢复任务
     * @param jobId
     * @return Object
     */
    @RequestMapping("/resumeJob")
    public Object resumeJob(String jobId) {
        try {
            quartzService.resumeJob(jobId);
            return success("resumeJob success");
        } catch (Exception e) {
            LOG.error("resumeJob Exception", e);
            return fail(e);
        }
    }

    /**
     * 添加定时任务
     * @param job
     * @return Object
     */
    @RequestMapping("/addQuartzJob")
    public Object addQuartzJob(QuartzJob job) {
        try {
            quartzService.addQuartzJob(job);
            return success("addQuartzJob success");
        } catch (Exception e) {
            LOG.error("addQuartzJob Exception", e);
            return fail(e);
        }
    }


}
