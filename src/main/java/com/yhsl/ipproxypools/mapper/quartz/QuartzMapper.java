package com.yhsl.ipproxypools.mapper.quartz;

import com.yhsl.ipproxypools.domain.QuartzJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartzMapper {
    void add(QuartzJob quartzJob);

    List<QuartzJob> getAll();

    QuartzJob getById(String id);

    void uopdateJob(QuartzJob quartzJob);
}
