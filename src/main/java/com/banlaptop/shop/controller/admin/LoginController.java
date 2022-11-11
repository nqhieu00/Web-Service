package com.banlaptop.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {
    @RequestMapping(value = {"/loginAdmin"})
    public ModelAndView loginAdmin(){
        ModelAndView modelAndView=new ModelAndView("/admin/layouts/login");
        return modelAndView;
    }
    @RequestMapping(value = "/403")
    public ModelAndView DeniedPage(Principal principal){
        ModelAndView modelAndView=new ModelAndView("redirect:/loginAdmin?denied");
        return modelAndView;
    }

}
