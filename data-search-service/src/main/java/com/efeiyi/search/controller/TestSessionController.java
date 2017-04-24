package com.efeiyi.search.controller;

import com.efeiyi.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
@Controller
public class TestSessionController {



    @RequestMapping(value="/test")
    public String test(HttpServletRequest request, HttpServletResponse response, Model model){

        Object username =  request.getSession().getAttribute("username");
        model.addAttribute(username);
        return "index";


    }
    @RequestMapping(value="/set")
    public void setattribute(HttpServletRequest request, String username, HttpServletResponse response){

        request.getSession().setAttribute("username",username);
        System.out.println( request.getRemoteHost()+":"+username);

        try {
            response.getWriter().write(request.getRemoteHost()+":"+username);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
