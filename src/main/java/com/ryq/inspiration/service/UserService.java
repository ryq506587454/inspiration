package com.ryq.inspiration.service;

import com.ryq.inspiration.pojo.User;

public interface UserService {
    User login(User record);
    int Register(User record);

}
