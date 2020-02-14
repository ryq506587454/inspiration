package com.ryq.inspiration;

import com.ryq.inspiration.pojo.User;
import com.ryq.inspiration.serviceImp.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired(required = false)
    private UserServiceImp userServiceImp;
    @Test
    public void Login(){
        User record = new User("1","888888");
        User user = userServiceImp.login(record);
        if (user != null){
            System.out.println(user.getName());
        }else{
            System.out.println(123);
        }
    }

    @Test
    public void register(){
        User new_user = new User("tan","123456");
        System.out.println(userServiceImp.Register(new_user));
    }
}
