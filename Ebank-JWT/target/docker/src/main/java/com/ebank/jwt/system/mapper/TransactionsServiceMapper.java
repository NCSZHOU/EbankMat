package com.ebank.jwt.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebank.jwt.system.entity.Transaction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransactionsServiceMapper extends BaseMapper {
    @Select({
            "<script>" +
            "SELECT * FROM transaction \n" +
            "  WHERE userid = #{userid}",
            "</script>"})
    List<Transaction> findTransactionsByUserName(@Param("userid") String userid);
}
