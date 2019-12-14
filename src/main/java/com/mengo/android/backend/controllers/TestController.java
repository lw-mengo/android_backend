package com.mengo.android.backend.controllers;

import com.mengo.android.backend.beans.User;
import com.mengo.android.backend.serviceImpl.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {


    @Autowired
    private UserServices userServices;

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAllUser() {
        List<User> users = userServices.getAll();
        return users;
    }

    @RequestMapping("/loginAction")
    @ResponseBody
    public String loginAction(@RequestParam("username") String username, @RequestParam("pwd") String pwd) {
        if (username.equals("admin")) {
            if (pwd.equals("159357")) {
                return "success";
            } else {
                return "password error!";
            }
        } else {
            return "username error!";
        }
    }
}
