package com.swimmingliu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swimmingliu.common.exception.BizIllegalException;
import com.swimmingliu.common.utils.BeanUtils;
import com.swimmingliu.domain.dto.ItemDTO;
import com.swimmingliu.domain.dto.OrderDetailDTO;
import com.swimmingliu.domain.po.Item;
import com.swimmingliu.mapper.ItemMapper;
import com.swimmingliu.service.IItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.swimmingliu.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足!", e);
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
