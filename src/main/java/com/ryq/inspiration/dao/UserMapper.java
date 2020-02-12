package com.ryq.inspiration.dao;

import com.ryq.inspiration.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserIdPSW(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}