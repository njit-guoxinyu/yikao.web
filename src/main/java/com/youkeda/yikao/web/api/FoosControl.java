package com.youkeda.yikao.web.api;

import com.youkeda.yikao.web.config.NeedLogin;
import com.youkeda.yikao.web.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoosControl {
    @RequestMapping(path="/api/foos")
    @NeedLogin
    public String getFoos(User user){
        return "Parameters:"+user.getId()+":"+user.getPwd();
    }
}
