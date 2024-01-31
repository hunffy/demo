package com.example.demo.mapper;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    void saveUser(User user);
    User loginUser(User user);
    User getUserById(String id);
}
