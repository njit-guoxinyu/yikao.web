package com.youkeda.yikao.web.control;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

//验证码服务
@Controller
public class CaptchaControl {

    //自动依赖注入，获取DefaultKaptcha的实例
    @Autowired
    private DefaultKaptcha kaptcha;

    //@Bean的作用：通过@Bean在应用启动时创建一个Spring Bean的实例
    //完成初始化Kaptcha
    @Bean
    public DefaultKaptcha initKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
//        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789abc");
        //验证码文本字符内容范围
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"6");
        //参数都是字符串
        properties.put(Constants.KAPTCHA_IMAGE_WIDTH,"140" );
        properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,"30" );
        properties.put(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,"com.google.code.kaptcha.impl.WaterRipple" );
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

    //处理HTTP头部信息
    @RequestMapping(path = "/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        //设置HTTP Cache 响应头
        //Set to expire far in the past.设置过期的时间期限
        response.setDateHeader("Expires", 0);
        //Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        //Set lE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0,pre-check=0");
        //Set standard HTTP/1.0 no cache header.
        response.setHeader("Pragma", "no-cache");
        //4句话作用：客户端版本有多种，我们设置4种，为了让验证码图片不要有浏览器的本地Cache.
        //即：阻止JSP或者Servlet中的输出被浏览器保存在缓冲区中

        //return a jpeg,确保访问/captcha时访问的是图片格式的内容
        response.setContentType("image/png");

        //创建随机数，框架已提供
        //create the text for the image
        String capText = kaptcha.createText();
        //store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        //create the image with the text
        BufferedImage bi = kaptcha.createImage(capText);
        //write the data out
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "png", out);
        try {
            out.flush();
        } finally {
            out.close();
        }


    }
}