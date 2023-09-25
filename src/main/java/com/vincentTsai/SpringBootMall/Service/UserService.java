package com.vincentTsai.SpringBootMall.Service;

import com.vincentTsai.SpringBootMall.DTO.UserLoginRequest;
import com.vincentTsai.SpringBootMall.DTO.UserRegisterRequest;
import com.vincentTsai.SpringBootMall.modal.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}
