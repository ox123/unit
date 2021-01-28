package org.mybatis.shop.dao;

import org.mybatis.shop.domain.Customer;

public interface CustomerMapper {
    // 根据用户Id查询Customer(不查询Address)
    Customer find(long id);

    // 根Id查询Customer(同时查询Address)
    Customer findWithAddress(long id);

    // 根据orderId查询Customer
    Customer findByOrderId(long orderId);

    // 持久化Customer对象据用户
    int save(Customer customer);
}
