package com.security.controller;

import com.security.model.Admin;
import com.security.repository.LoginRepository;
import com.security.service.MyUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by app on 2016/9/1.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private MyUserService myUserService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(@RequestParam(value ="error",required = false) boolean error,ModelMap modelMap){

        if(error){
            modelMap.put("error","You have entered an invalid username or password!");
        }else{
            //myUserService.setLoginRepository(loginRepository);
            modelMap.put("error","");
        }
        return "login";
    }

    @RequestMapping(value = "/safe",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "Congratulations!,your access the Auth";
    }

    @RequestMapping(value = "/fail",method = RequestMethod.GET)
    public String pageFail(){
        return "fail";
    }

    @RequestMapping(value = "/timeout",method = RequestMethod.GET)
    public String pageTimeout(){
        return "timeout";
    }

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String pageSuccess(){
        return "success";
    }

    @RequestMapping("/admin")
    public String pageAdmin(){
        return "index";
    }

    @RequestMapping(value = "/json",method = RequestMethod.GET)
    public @ResponseBody List<Admin> getUsers(){

        return loginRepository.findAll();
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@ModelAttribute("userlogin") Admin admin, ModelMap modelMap){
        int num=loginRepository.login(admin.getuName(),admin.getPwd());
        if(num>0){
            modelMap.addAttribute("user",admin);
            return "success";
        }else{
            return "index";
        }
    }

    @RequestMapping(value = "/user/logining",method = RequestMethod.POST)
    public String login2(@RequestParam("username")String username,@RequestParam("password")String password, ModelMap modelMap){
        int num=loginRepository.login(username,username);
        if(num>0){
            modelMap.addAttribute("user",username);
            return "success";
        }else{
            return "index";
        }
    }
}
