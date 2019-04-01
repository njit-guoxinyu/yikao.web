package com.youkeda.yikao.web.control;

import com.google.code.kaptcha.Constants;
import com.youkeda.yikao.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {

    @RequestMapping(path = "/login")
//    @NeedLogin
    public ModelAndView login(User user,
                              @RequestParam("captcha") String captcha ,
                              HttpSession session){
        if (!captcha.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return new ModelAndView("redirect:login.html");
        }

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
