package com.mengo.android.backend.mapper;

import com.mengo.android.backend.beans.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> get();
}
