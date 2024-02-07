package com.example.demo.service; 

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class UserService {
    //회원가입시 저장시간을 넣어주는 부분
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Autowired
    UserMapper userMapper;

    @Transactional
    
    //회원가입
    public boolean joinUser(User user){

        if (user.getUser_id() == null || user.getUser_id().isEmpty() ||
            user.getPassword() == null || user.getPassword().isEmpty() ||
         user.getUser_name() == null || user.getUser_name().isEmpty()) {
            String errorMessage = "필수 정보를 입력하세요.";
            String script = String.format("alert('%s');",errorMessage);
            return false;
        }
    // 회원가입 처리 로직

    
        // 입력값이 유효한지 검사
        if (user.getUser_id() == null || user.getPassword() == null || user.getUser_name() == null) {
            // 필수 입력값이 비어있을 경우
            if (user.getUser_id() == null) {
                // 아이디가 비어있을 경우
                throw new IllegalArgumentException("아이디를 입력해주세요");
            } else if (user.getPassword() == null) {
                // 비밀번호가 비어있을 경우
                throw new IllegalArgumentException("비밀번호를 입력해주세요");
            } else {
                // 이름이 비어있을 경우
                throw new IllegalArgumentException("이름을 입력해주세요");
            }
        }
        
        // 중복된 아이디가 있는지 확인
        if (userMapper.getUserById(user.getUser_id()) != null) {
            // 이미 존재하는 아이디일 경우
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.saveUser(user);
    
        return true;
    }

    //로그인
    public User getUserById(String id){
        return userMapper.getUserById(id);
    }

}

