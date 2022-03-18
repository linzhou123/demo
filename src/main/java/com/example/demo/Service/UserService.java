package com.example.demo.Service;

import com.example.demo.Model.User;

/**
 * @创建人 Linzhou
 * @创建时间 2021/10/29
 * @描述
 **/

public interface UserService {
    boolean login( User user);
    String save(User user);
}
