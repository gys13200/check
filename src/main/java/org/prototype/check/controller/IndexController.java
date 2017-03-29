package org.prototype.check.controller;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/admin-index")
    public String index(){
        return "admin-index";
    }

    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public String hub(@PathVariable String path){

        System.err.println("============ hub:" + path);

        return path;
    }
}
