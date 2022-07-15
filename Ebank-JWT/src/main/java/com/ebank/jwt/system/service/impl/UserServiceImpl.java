package com.ebank.jwt.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.ebank.jwt.system.entity.User;
import com.ebank.jwt.system.mapper.UserMapper;
import com.ebank.jwt.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Greyson
 * @since 2022-07-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper sysUserMapper;

    @Override
    public User getUserByUserName(String userName){
        Assert.isTrue(StrUtil.isNotEmpty(userName),
                "User is not exist");

        User sysUser = sysUserMapper.selectOne(
                new QueryWrapper<User>().eq("user_name",userName));
        if(sysUser != null){
            sysUser.setPassword("");
        }
        return sysUser;
    }
}
