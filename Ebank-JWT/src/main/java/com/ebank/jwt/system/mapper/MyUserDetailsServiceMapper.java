package com.ebank.jwt.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebank.jwt.system.entity.User;
import com.ebank.jwt.util.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MyUserDetailsServiceMapper extends BaseMapper {
    @Select("SELECT user_name,password,enabled\n" +
            "FROM user u\n" +
            "WHERE u.user_name = #{username}")
    MyUserDetails findByUserName(@Param("username") String username);

    @Select({
            "<script>" +
            "SELECT ur.roleid FROM user_role ur LEFT JOIN user u ON  ur.userid = u.id\n" +
            "  WHERE u.user_name = #{username}",
            "</script>"})
    List<String> findRoleByUserName(@Param("username") String username);

    @Select("SELECT *\n" +
            "FROM user u\n" +
            "WHERE u.user_name = #{username}")
    User findUserByUserName(@Param("username") String username);
}
