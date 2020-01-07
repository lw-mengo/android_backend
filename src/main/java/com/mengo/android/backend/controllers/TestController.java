package com.mengo.android.backend.controllers;

import com.mengo.android.backend.beans.User;
import com.mengo.android.backend.serviceImpl.UserServices;
import com.mengo.android.backend.utils.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {


    private final UserServices userServices;

    public TestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAllUser() {
        return userServices.getAll();
    }

    @RequestMapping("/loginAction")
    @ResponseBody
    public String loginAction(@RequestParam("username") String username, @RequestParam("pwd") String pwd) {
        User user = userServices.getUserByName(username);
        ResultJson resultJson = new ResultJson();
        if (user != null) {
            if (user.getPassword().equals(pwd)) {
                return resultJson.success();
            } else {
                return resultJson.error("password error");
            }
        } else {
            return resultJson.error("username error");
        }
    }
}
