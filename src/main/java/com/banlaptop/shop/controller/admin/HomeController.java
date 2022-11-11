package com.banlaptop.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @RequestMapping("/admin")
    public ModelAndView home(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/home");

        return modelAndView;
    }
}
