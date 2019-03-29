package com.youkeda.yikao.web.api;

import com.youkeda.yikao.web.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class Fooscontrol{
//    @RequestMapping(path = "/api/foos")
//    public String getFoos(User user){
//        return"Parameters:"+user.getId()+"："+user.getName()+"："+user.getTitle();
//
//    @RequestMapping(path="/ex/foo/{id")
//    public String getFoosByPathvalue(@PathVariable("id")long id){
//            return "get:" + id;
//        }
//
//    @RequestMapping(path="/ex/foo/{id:/bar/{barid:[\\d]+}",
//                consumes="application/json",
//                produces="application/json")
//        public String getFoosByPathValue2(@PathVariable("id")long id,@PathVariable("barid")long barid,
//        @RequestHeader("key1")String key1){
//            return "get:" + id + "：" + barid + "：" + keyl;
//        }
