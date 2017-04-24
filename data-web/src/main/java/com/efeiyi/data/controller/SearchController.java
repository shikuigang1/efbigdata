package com.efeiyi.data.controller;

import com.efeiyi.data.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
@Controller
public class SearchController {

    @Autowired
    private ISearchService searchService;
    @RequestMapping(value="search")
    public void search(HttpServletRequest request, HttpServletResponse response,String searchText){



    }
}
