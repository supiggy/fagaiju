package com.scu.fagaiju.service;

import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.User;
import com.scu.fagaiju.mapper.UserMapper;

import java.io.Serializable;

public interface UserService extends Serializable {
    //
    PageInfo<User> findUsers(int page,int limit);
    User findUserByLoginName(User user);

    User getUserById(Integer id);

    void save(User user);

    int removeUserById(Integer... id);
}
