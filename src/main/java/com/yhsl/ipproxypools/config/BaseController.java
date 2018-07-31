package com.yhsl.ipproxypools.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    protected Map<String, Object> success(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj != null) {
            map.put("data", obj);
        }
        map.put("status", 200);
        return map;
    }

    protected Map<String, Object> fail(Throwable cause) {
        Map<String, Object> map = new HashMap<String, Object>();
        String errorMsg = "system fail";
        if (cause instanceof BusinessException) {
            map.put("errorCode", ((BusinessException) cause).getErrorCode());
            errorMsg = cause.getMessage();
        }
        map.put("msg", errorMsg);
        map.put("success", false);
        map.put("status", 500);
        return map;
    }


}
