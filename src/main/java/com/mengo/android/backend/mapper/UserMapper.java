package com.mengo.android.backend.mapper;

import com.mengo.android.backend.beans.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> get();

    User getUserByName(String username);

    void registerUser(User user);//update，delete，insert无须返回类型，默认都是返回一个int
}
