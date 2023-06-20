package com.mjw.miao.service;

import com.mjw.miao.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface UserService {

    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
