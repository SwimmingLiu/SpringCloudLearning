package com.swimmingliu.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swimmingliu.item.domain.dto.ItemDTO;
import com.swimmingliu.item.domain.dto.OrderDetailDTO;
import com.swimmingliu.item.domain.po.Item;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface IItemService extends IService<Item> {

    void deductStock(List<OrderDetailDTO> items);

    List<ItemDTO> queryItemByIds(Collection<Long> ids);
}
