package com.swimmingliu.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swimmingliu.item.domain.dto.OrderDetailDTO;
import com.swimmingliu.item.domain.po.Item;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
public interface ItemMapper extends BaseMapper<Item> {

    @Update("UPDATE item SET stock = stock - #{num} WHERE id = #{itemId}")
    void updateStock(OrderDetailDTO orderDetail);
}
