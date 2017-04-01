package org.prototype.check.base.controller;

import org.prototype.check.base.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gys on 2017/3/31.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    public String index(Model model){

        List<User> userList = new ArrayList<User>();
        Random random = new Random();
        for(int i=0; i<10; i++){
            User user = new User();
            user.setGender(random.nextInt(4));
            user.setEmail("gys13200@163.com");
            user.setUserId(i + "");
            user.setTelNum("12300000000");
            user.setUsername("username" + i);
            userList.add(user);
        }
        model.addAttribute("userList", userList);
        return "user/user-list";
    }
}
