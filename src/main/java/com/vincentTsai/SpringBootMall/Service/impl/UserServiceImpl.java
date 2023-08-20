package com.vincentTsai.SpringBootMall.Service.impl;

import com.vincentTsai.SpringBootMall.DAO.UserDao;
import com.vincentTsai.SpringBootMall.DTO.UserRegisterRequest;
import com.vincentTsai.SpringBootMall.Service.UserService;
import com.vincentTsai.SpringBootMall.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


}
