package com.youkeda.yikao.web;

import com.youkeda.yikao.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {

    @RequestMapping(path = "login")
    public ModelAndView login(User user, HttpSession session){

        if (user.getName().equals("admin@admin.com")&& user.getPwd().equals("Bmatch123")){
            session.setAttribute("user", user);
            return new ModelAndView("redirect:index.html");
        }
            return new ModelAndView("redirect:login.html");
    }
}
