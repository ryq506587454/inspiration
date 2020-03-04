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

@Service("userService")
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User record) {
        return userMapper.selectByUserIdPSW(record);
    }

    @Override
    public int Register(User record) {
        return userMapper.insert(record);
    }

    @Override
    public String upLoadUserImage(MultipartFile file, String uploadPath,User record) {
        try {
            if (!Files.exists(Paths.get(uploadPath))){
                System.out.println(1234);
                Files.createDirectories(Paths.get(uploadPath));
            }
            byte[] bytes = file.getBytes();
            String UserImage = uploadPath+file.getOriginalFilename();
            Path path = Paths.get(UserImage);
            Files.write(path,bytes);
            record.setImage("img/"+record.getId()+"/"+file.getOriginalFilename());
            userMapper.updateByPrimaryKeySelective(record);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
