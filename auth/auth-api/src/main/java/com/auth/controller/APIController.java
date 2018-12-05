package com.auth.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhiwen on 2017/8/10.
 */
@RestController
@RequestMapping(value = "api")
public class APIController {

    @GetMapping("hello")
    public Map<String, Object> hello() {
        Map<String, Object> data = Maps.newHashMap();
        data.put("Hello", "API");
        return data;
    }
}
