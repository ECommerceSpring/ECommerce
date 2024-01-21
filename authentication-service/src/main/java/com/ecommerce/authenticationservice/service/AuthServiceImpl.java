package com.ecommerce.authenticationservice.service;


import com.ecommerce.authenticationservice.entity.UserInfo;
import com.ecommerce.authenticationservice.repository.UserInfoRepository;
import com.ecommerce.authenticationservice.utility.Md5Util;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserInfoRepository userInfoRepository;

    public AuthServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo findByUsername(String username) {
        Optional<UserInfo> result = userInfoRepository.findByUsername(username);

        UserInfo userInfo = null;

        if (result.isPresent()) {
            userInfo = result.get();
        }

        return userInfo;
    }

    public UserInfo findByEmail(String email) {
        Optional<UserInfo> result = userInfoRepository.findByEmail(email);

        UserInfo userInfo = null;

        if (result.isPresent()) {
            userInfo = result.get();
        }

        return userInfo;
    }

    public UserInfo createUserProfile(UserInfo userInfo) {
        userInfo.setPassword(Md5Util.getInstance().getMd5Hash(userInfo.getPassword()));
        return userInfoRepository.save(userInfo);
    }

    public void deleteByUsernamePassword(String username, String password) throws NoSuchAlgorithmException {
        userInfoRepository.deleteByUsernameAndPassword(username, Md5Util.getInstance().getMd5Hash(password));
    }
}
