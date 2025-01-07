package com.swimmingliu.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swimmingliu.trade.domain.po.OrderDetail;
import com.swimmingliu.trade.mapper.OrderDetailMapper;
import com.swimmingliu.trade.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
