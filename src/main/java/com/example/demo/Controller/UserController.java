package com.example.demo.Controller;

import com.example.demo.Model.ResponseInfo;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 Linzhou
 * @创建时间 2021/10/29
 * @描述
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;
    @PostMapping("/save")
    public ResponseInfo save(@RequestBody User user){
        return ResponseInfo.successInfo(userService.save(user));
    }
    @PostMapping("/login")
    public ResponseInfo login(HttpServletResponse response,@RequestBody User user){

        if (userService.login(user)){
            Cookie cookie =new Cookie("userName",user.getUserName());
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseInfo.successInfo(cookie.toString());
        }else {
            return ResponseInfo.successInfo("账号密码错误");
        }
    }
    @GetMapping("/page")
    public ResponseInfo page(){
        return ResponseInfo.successInfo("");
    }

}
