package com.efeiyi.data.controller;

import com.efeiyi.data.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;
    @ResponseBody
    @RequestMapping(value = "/test")
    public void sayIp(){
        System.out.println(demoService.sayIp());
    }
}
