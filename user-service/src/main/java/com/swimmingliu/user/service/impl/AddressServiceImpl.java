package com.swimmingliu.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swimmingliu.user.domain.po.Address;
import com.swimmingliu.user.mapper.AddressMapper;
import com.swimmingliu.user.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SwimmingLiu
 * @author 2025-01-01
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
