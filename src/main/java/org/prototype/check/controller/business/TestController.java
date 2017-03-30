package org.prototype.check.controller.business;

import org.prototype.check.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gys on 2017/3/30.
 */

@RequestMapping("/business")
@Controller
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping("/user")
    public void user(){
        service.user();
    }
}
