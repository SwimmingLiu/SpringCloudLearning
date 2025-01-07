package com.swimmingliu.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swimmingliu.pay.domain.dto.PayApplyDTO;
import com.swimmingliu.pay.domain.dto.PayOrderFormDTO;
import com.swimmingliu.pay.domain.po.PayOrder;


/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author SwimmingLiu
 * @since 2025-01-01
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
