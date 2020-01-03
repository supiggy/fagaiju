package com.scu.fagaiju.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scu.fagaiju.common.domain.User;
import com.scu.fagaiju.common.domain.UserExample;
import com.scu.fagaiju.common.utils.Result;
import com.scu.fagaiju.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<User> findUsers(int page, int limit) {
        List<User> list = userMapper.selectByExample(null);
        PageHelper.startPage(page,limit);
        return new PageInfo<>(list);
    }

    @Override
    public User findUserByLoginName(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> list = userMapper.selectByExample(example);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int removeUserById(Integer... id) {
        UserExample example = new UserExample();
        example.createCriteria().andIdIn(Arrays.asList(id));
        return userMapper.deleteByExample(example);
    }

    @Override
    public void save(User user) {
        if(user.getId() == null || "".equals(user.getId())) {
            userMapper.insert(user);
        } else {
            userMapper.updateByPrimaryKey(user);
        }
    }


}
