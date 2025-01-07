package com.swimmingliu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swimmingliu.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set balance = balance - ${totalFee} where id = #{userId}")
    void updateMoney(@Param("userId") Long userId, @Param("totalFee") Integer totalFee);
}
