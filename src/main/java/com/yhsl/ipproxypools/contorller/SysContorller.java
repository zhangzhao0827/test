package com.yhsl.ipproxypools.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysContorller {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
