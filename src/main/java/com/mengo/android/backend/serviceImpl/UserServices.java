package com.mengo.android.backend.serviceImpl;

import com.mengo.android.backend.beans.User;
import com.mengo.android.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.get();
    }
}
