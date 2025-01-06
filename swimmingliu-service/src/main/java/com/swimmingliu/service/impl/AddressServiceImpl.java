package com.swimmingliu.service.impl;

import com.swimmingliu.domain.po.Address;
import com.swimmingliu.mapper.AddressMapper;
import com.swimmingliu.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
