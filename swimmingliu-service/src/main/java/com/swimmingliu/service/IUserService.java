package com.swimmingliu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swimmingliu.domain.dto.LoginFormDTO;
import com.swimmingliu.domain.po.User;
import com.swimmingliu.domain.vo.UserLoginVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
