package com.youkeda.yikao.web.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class EmailControl {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DefaultKaptcha kaptcha;

    @RequestMapping(path = "sendmail")
    @ResponseBody //因为return的是字符串
    public String send(HttpSession session){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gxy827155369@163.com");
        message.setTo("827155369@qq.com");
        message.setSubject("验证码");

        //使用随机数来发送
        String emailcode =kaptcha.createText();
        session.setAttribute("emailcode", emailcode);
        message.setText(emailcode);

        javaMailSender.send(message);
        return "发送邮件成功";
    }

}
