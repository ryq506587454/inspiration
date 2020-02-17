package com.ryq.inspiration.serviceImp;

import com.ryq.inspiration.dao.UserMapper;
import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Override
    public String upLoadUserImage(MultipartFile file, String uploadPath) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath);
            Files.write(path,bytes);
            return "上传成功";
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
