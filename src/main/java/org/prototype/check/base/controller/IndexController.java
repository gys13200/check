package org.prototype.check.base.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.prototype.check.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * Created by gys on 2017/3/28.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        try{
            if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                throw new AuthenticationException("用户名或密码为空");
            }
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
            user.login(token);
            token.setRememberMe(true);
        }catch (AuthenticationException e){
            model.addAttribute("loginErrorMsg", "用户名或者密码错误");
            model.addAttribute("username", username);
            return "login";
        }
        return "admin-index";
    }

    @RequestMapping(value = "/static/{path}", method = RequestMethod.GET)
    public String hub(@PathVariable String path){
        return path;
    }
}
