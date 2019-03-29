package com.youkeda.yikao.web;

import com.youkeda.yikao.web.config.NeedLogin;
import com.youkeda.yikao.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {

    @RequestMapping(path = "/login")
//    @NeedLogin
    public ModelAndView login(User user, HttpSession session){
        if (user.getId().equals("admin")&& user.getPwd().equals("admin")){
            session.setAttribute("user", user);
            return new ModelAndView("redirect:index.html");
        }
            return new ModelAndView("redirect:login.html");
    }



    @RequestMapping(path = "logout")
    @ResponseBody
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "注销登录";
    }


}
