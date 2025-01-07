package com.swimmingliu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swimmingliu.domain.dto.CartFormDTO;
import com.swimmingliu.domain.po.Cart;
import com.swimmingliu.domain.vo.CartVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface ICartService extends IService<Cart> {

    void addItem2Cart(CartFormDTO cartFormDTO);

    List<CartVO> queryMyCarts();

    void removeByItemIds(Collection<Long> itemIds);
}
