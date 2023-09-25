package com.vincentTsai.SpringBootMall.Service.impl;

import com.vincentTsai.SpringBootMall.DAO.UserDao;
import com.vincentTsai.SpringBootMall.DTO.UserLoginRequest;
import com.vincentTsai.SpringBootMall.DTO.UserRegisterRequest;
import com.vincentTsai.SpringBootMall.Service.UserService;
import com.vincentTsai.SpringBootMall.modal.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;



@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //查詢email是否已註冊
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){
            log.warn("該帳號{}已被註冊", userRegisterRequest.getEmail());
            //指定回傳400給前端
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 建立帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //確認email是否有註冊
        if (user == null){
            log.warn("該Email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //確認密碼
        if (user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        } else {
            log.warn("密碼錯誤");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
