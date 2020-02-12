package com.ryq.inspiration.serviceImp;

import com.ryq.inspiration.dao.UserMapper;
import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public User login(User record) {
        User user = userMapper.selectByUserIdPSW(record);
        return user;
    }

    @Override
    public int Register(User record) {
        int result = userMapper.insert(record);
        return result;
    }
}
