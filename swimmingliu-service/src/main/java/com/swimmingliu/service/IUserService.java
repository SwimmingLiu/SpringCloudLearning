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
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
