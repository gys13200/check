package org.prototype.check.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gys on 2017/3/28.
 */

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController{

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model){
        int status = response.getStatus();
        model.addAttribute("errorStatus", status);

        if(status >= 400 && status < 500){
            model.addAttribute("errorTitle", status + ", Not Found");
            model.addAttribute("errorMsg", "没有找到你要的页面");
        }else{
            model.addAttribute("errorTitle", status + ", Server Error");
            model.addAttribute("errorMsg", "服务器好像开小差了");
        }
        return "admin-404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
