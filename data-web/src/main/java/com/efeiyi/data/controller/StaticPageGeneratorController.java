package com.efeiyi.data.controller;

import com.efeiyi.data.service.IPageStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
@Controller
@RequestMapping("/page")
public class StaticPageGeneratorController {

    @Autowired
    private IPageStaticService pageServie;

    @ResponseBody
    @RequestMapping(value = "/generate")
    public void generatePage(){
        pageServie.generatePage();
    }
}
