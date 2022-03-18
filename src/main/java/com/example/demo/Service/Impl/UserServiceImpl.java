package com.example.demo.Service.Impl;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.Model.Response.User.LoginResp;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.example.demo.utils.DateToStamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @创建人 Linzhou
 * @创建时间 2021/10/29
 * @描述
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean login(User user) {
        LoginResp loginResp = new LoginResp();
        String password = userMapper.selectByUserName(user.getUserName()).getPassword();
        if (password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String save(User user) {
        if (userMapper.selectByUserName(user.getUserName()) != null) {
            return "账号已存在";
        } else {
            user.setCreateTime(DateToStamp.getTimeStap());
            user.setUpdateTime(DateToStamp.getTimeStap());
            userMapper.insert(user);
            return "";
        }
    }
}
