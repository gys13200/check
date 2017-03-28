package org.prototype.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gys on 2017/3/28.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "admin-index";
    }
}
