package com.swimmingliu.service;

import com.swimmingliu.domain.dto.PayApplyDTO;
import com.swimmingliu.domain.dto.PayOrderFormDTO;
import com.swimmingliu.domain.po.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author SwimmingLiu
 * @since 2023-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
