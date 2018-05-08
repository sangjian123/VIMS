package com.vms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class RouterController
{
    
    @RequestMapping (value = "/login")
    public String login()
    {
        return "login/login";
    }
    
    @RequestMapping (value = "/home")
    public String home()
    {
        return "home/home";
    }
    
    @RequestMapping (value = "/basicInfo")
    public String basicInfo()
    {
        return "service/basicInfo";
    }
    
    @RequestMapping (value = "/managementSchedue")
    public String managementSchedue()
    {
        return "service/managementSchedue";
    }
}
