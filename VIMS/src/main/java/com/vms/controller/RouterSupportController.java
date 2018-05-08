package com.vms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class RouterSupportController
{
    
    @RequestMapping (value = "support")
    public String login()
    {
        return "support/main";
    }
    
    @RequestMapping (value = "roleManager")
    public String roleManager()
    {
        return "support/roleManager";
    }
    
    @RequestMapping (value = "deptManager")
    public String deptManager()
    {
        return "support/deptManager";
    }
    
}
