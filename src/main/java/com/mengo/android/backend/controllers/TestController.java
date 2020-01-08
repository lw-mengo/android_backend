package com.mengo.android.backend.controllers;

import com.mengo.android.backend.beans.User;
import com.mengo.android.backend.serviceImpl.UserServices;
import com.mengo.android.backend.utils.PasswordUtil;
import com.mengo.android.backend.utils.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

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

    /**
     * 用户登录
     */
    @RequestMapping("/loginAction")
    @ResponseBody
    public String loginAction(@RequestParam("username") String username, @RequestParam("pwd") String pwd) throws NoSuchAlgorithmException {
        User user = userServices.getUserByName(username);
        ResultJson resultJson = new ResultJson();
        if (user != null) {
            String salt = user.getSalt_word();
            if (user.getPassword().equals(PasswordUtil.encryptionPassword(pwd, salt))) {
                return resultJson.success();
            } else {
                return resultJson.error("password error");
            }
        } else {
            return resultJson.error("username error");
        }
    }

    /**
     * 用户注册
     *
     * @param username
     * @param pwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/registerAction", method = RequestMethod.POST)
    @ResponseBody
    public String RegisterAction(@RequestParam("username") String username, @RequestParam("pwd") String pwd) throws NoSuchAlgorithmException {
        ResultJson resultJson = new ResultJson();
        if (userServices.getUserByName(username) != null) {
            return resultJson.error("user exist!");
        }
        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0, 8);//创建一个盐
        String password = PasswordUtil.encryptionPassword(pwd, salt);
        user.setUser_name(username);
        user.setPassword(password);
        user.setSalt_word(salt);
        userServices.registerUser(user);
        return resultJson.success();
    }
}
