package com.swimmingliu.cart.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swimmingliu.cart.domain.dto.CartFormDTO;
import com.swimmingliu.cart.domain.po.Cart;
import com.swimmingliu.cart.domain.vo.CartVO;
import com.swimmingliu.cart.mapper.CartMapper;
import com.swimmingliu.cart.service.ICartService;
import com.swimmingliu.common.exception.BizIllegalException;
import com.swimmingliu.common.utils.BeanUtils;
import com.swimmingliu.common.utils.CollUtils;
import com.swimmingliu.common.utils.UserContext;
import com.swimmingliu.api.client.ItemClient;
import com.swimmingliu.api.domain.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    //    private final IItemService itemService;
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final ItemClient itemClient;

    @Override
    public void addItem2Cart(CartFormDTO cartFormDTO) {
        // 1.获取登录用户
        Long userId = UserContext.getUser();

        // 2.判断是否已经存在
        if (checkItemExists(cartFormDTO.getItemId(), userId)) {
            // 2.1.存在，则更新数量
            baseMapper.updateNum(cartFormDTO.getItemId(), userId);
            return;
        }
        // 2.2.不存在，判断是否超过购物车数量
        checkCartsFull(userId);

        // 3.新增购物车条目
        // 3.1.转换PO
        Cart cart = BeanUtils.copyBean(cartFormDTO, Cart.class);
        // 3.2.保存当前用户
        cart.setUserId(userId);
        // 3.3.保存到数据库
        save(cart);
    }

    @Override
    public List<CartVO> queryMyCarts() {
        // 1.查询我的购物车列表
//        List<Cart> carts = lambdaQuery().eq(Cart::getUserId, UserContext.getUser()).list();
        List<Cart> carts = lambdaQuery().eq(Cart::getUserId, 1L).list();
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.转换VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.处理VO中的商品信息
        handleCartItems(vos);

        // 4.返回
        return vos;
    }

    private void handleCartItems(List<CartVO> vos) {
        // 1.获取商品id
        Set<Long> itemIds = vos.stream().map(CartVO::getItemId).collect(Collectors.toSet());
        // 2. 查询商品 -> OpenFeign -> nacos 获取主机地址
        List<ItemDTO> items = itemClient.queryItemByIds(itemIds);
//        // 2. 查询商品 -> RestTemplate直接远程调用 -> nacos 获取主机地址
//        List<ServiceInstance> instances = discoveryClient.getInstances("item-service");
//        ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));
//        ResponseEntity<List<ItemDTO>> response = restTemplate.exchange(
//                instance.getUri() + "/items?ids={ids}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ItemDTO>>() {
//                },
//                Map.of("ids", CollUtils.join(itemIds, ","))
//        );
        // 2. 查询商品 -> RestTemplate直接远程调用
//        ResponseEntity<List<ItemDTO>> response = restTemplate.exchange("http://localhost:8081/items?ids={ids}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ItemDTO>>() {
//                },
//                Map.of("ids", CollUtils.join(itemIds, ","))
//        );
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            // 查询失败
//            return;
//        }
//        List<ItemDTO> items = response.getBody();
//        if (CollUtils.isEmpty(items)) {
//            // 查询失败，直接结束
//            return;
//        }
        // 2.查询商品 -> 单体
//        List<ItemDTO> items = itemService.queryItemByIds(itemIds);
//        if (CollUtils.isEmpty(items)) {
//            return;
//        }
        // 3.转为 id 到 item的map
        Map<Long, ItemDTO> itemMap = items.stream().collect(Collectors.toMap(ItemDTO::getId, Function.identity()));
        // 4.写入vo
        for (CartVO v : vos) {
            ItemDTO item = itemMap.get(v.getItemId());
            if (item == null) {
                continue;
            }
            v.setNewPrice(item.getPrice());
            v.setStatus(item.getStatus());
            v.setStock(item.getStock());
        }
    }

    @Override
    public void removeByItemIds(Collection<Long> itemIds) {
        // 1.构建删除条件，userId和itemId
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.lambda()
                .eq(Cart::getUserId, UserContext.getUser())
                .in(Cart::getItemId, itemIds);
        // 2.删除
        remove(queryWrapper);
    }

    private void checkCartsFull(Long userId) {
        Long count = lambdaQuery().eq(Cart::getUserId, userId).count();
        if (count >= 10) {
            throw new BizIllegalException(StrUtil.format("用户购物车课程不能超过{}", 10));
        }
    }

    private boolean checkItemExists(Long itemId, Long userId) {
        Long count = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .eq(Cart::getItemId, itemId)
                .count();
        return count > 0;
    }
}
