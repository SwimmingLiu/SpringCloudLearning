package com.swimmingliu.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swimmingliu.api.domain.po.Order;
import com.swimmingliu.trade.domain.dto.OrderFormDTO;


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
