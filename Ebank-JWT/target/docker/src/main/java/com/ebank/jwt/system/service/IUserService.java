package com.ebank.jwt.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebank.jwt.system.entity.User;

public interface IUserService extends IService<User> {
    User getUserByUserName(String userName);
}
