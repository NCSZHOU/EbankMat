package com.ebank.jwt.system.controller;


import com.ebank.jwt.system.entity.User;
import com.ebank.jwt.system.service.IUserService;
import com.ebank.jwt.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "UserController")
@RequestMapping("/transaction/user")
public class UserController {
    @Resource
    private IUserService sysuserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * Get the user information by login username
     * @return
     */
    @ApiOperation(value = "status")
    @GetMapping(value = "/status")
    public User info() {
        String username = jwtTokenUtil.getUsernameFromToken();
        if(StringUtils.isEmpty(username)) return null;
        return sysuserService.getUserByUserName(username);
    }
}

