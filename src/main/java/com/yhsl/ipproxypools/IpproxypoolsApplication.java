package com.yhsl.ipproxypools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yhsl.ipproxypools.mapper.*")
public class IpproxypoolsApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(IpproxypoolsApplication.class, args);
    }
}
