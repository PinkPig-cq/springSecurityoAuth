package com.qz.jwttoken.controller;


import com.qz.jwttoken.entity.Users;
import com.qz.jwttoken.utils.JwtTokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jwt")
public class UserLoginController {

    @RequestMapping("login")
    public String login(String account, String password, String captcha) {
        Users users = new Users();
        users.setAccount(account);
        users.setPassword(password);
        String token = JwtTokenUtil.createToken(users);
        return token;
    }

    @RequestMapping("delete")
    public String delete(String token,Integer id){
       Users users = JwtTokenUtil.decodeToken(token);
       //todo 判断逻辑

        return "";
    }
}
