package com.ryq.inspiration.service;

import com.ryq.inspiration.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User login(User record);
    int Register(User record);
    String upLoadUserImage(MultipartFile file, String path);
}
