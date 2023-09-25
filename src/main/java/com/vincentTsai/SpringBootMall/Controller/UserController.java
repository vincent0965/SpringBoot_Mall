package com.vincentTsai.SpringBootMall.Controller;

import com.vincentTsai.SpringBootMall.DTO.UserLoginRequest;
import com.vincentTsai.SpringBootMall.DTO.UserRegisterRequest;
import com.vincentTsai.SpringBootMall.Service.UserService;
import com.vincentTsai.SpringBootMall.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        //創建帳號
        Integer userId = userService.register(userRegisterRequest);
        //查詢帳號
        User user = userService.getUserById(userId);
        //將帳號回傳給前端
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
