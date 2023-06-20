package com.mjw.miao.mapper;

import com.mjw.miao.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int save(User user);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findByUserId(@Param("userId") Integer userId);
}
