package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PutMapping("")
    public UserEntity create(@RequestBody UserEntity userEntity){
        return userService.save(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findALl(){
        return userService.findAll();
    }
}
