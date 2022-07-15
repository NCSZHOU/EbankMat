package com.ebank.jwt.system.controller;

import cn.hutool.core.util.StrUtil;
import com.ebank.commons.BaseException;
import com.ebank.commons.api.Imp.ResultCode;
import com.ebank.jwt.constants.CommonConstants;
import com.ebank.jwt.constants.JWTConstants;
import com.ebank.commons.util.Result;
import com.ebank.jwt.config.JwtProperties;
import com.ebank.jwt.util.JwtAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Api(tags = "Jwt Authentication API")
@RequestMapping("/jwtauth")
public class JwtAuthController {
    private final static Logger logger = LoggerFactory.getLogger(JwtAuthController.class);
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private JwtAuthService jwtAuthService;

    /**
     * login to system to generate JWT Token
     */
    @ApiOperation(value = JWTConstants.CONTROLLER_AUTHENTICATION)
    @PostMapping(value = "/token")
    public Result login(@RequestBody Map<String,String> map){
        String username  = map.get(jwtProperties.getUserParamName());
        String password = map.get(jwtProperties.getPwdParamName());

        if(StrUtil.isEmpty(username)
                || StrUtil.isEmpty(password)){
            logger.error("Username or password is empty");
            return Result.fail(
                    ResultCode.Unauthorized,
                    "Username or password is null");
        }
        try {
            logger.info("Starting login:{}",username);
            return Result.data(
                    jwtAuthService.login(username, password,null));
        }catch (BaseException e){
            logger.error("Login failed:{}, message:{}",username,e.getMessage());
            return Result.fail(ResultCode.FAILURE,e.getMessage());
        }
    }
    /**
     * Refresh the jwt token
     */
    @ApiOperation(value = JWTConstants.CONTROLLER_REFRESH)
    @RequestMapping(value = JWTConstants.CONTROLLER_REFRESH)
    public  Result refresh(@RequestHeader(CommonConstants.HEADER) String token){
        return Result.data(jwtAuthService.refreshToken(token));
    }
}
