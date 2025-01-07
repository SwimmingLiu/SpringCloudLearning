package com.swimmingliu.service;

import com.swimmingliu.domain.dto.OrderFormDTO;
import com.swimmingliu.domain.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
