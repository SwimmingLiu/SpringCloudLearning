package com.swimmingliu.api.client;


import com.swimmingliu.api.domain.po.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface TradeClient {


    @PostMapping("/orders")
    Long updateById(@RequestBody Order order);
}