package com.example.demo.Mapper;

import com.example.demo.Model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @创建人 Linzhou
 * @创建时间 2021/10/29
 * @描述
 **/
public interface UserMapper {

    void insert(User user);
    User selectByUserName (@Param(value = "userName") String userName);
}
