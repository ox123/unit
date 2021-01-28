package org.mybatis.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.shop.domain.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    // 根据id查询OrderItem对象
    OrderItem find(long id);
    // 查询指定的订单中的全部OrderItem
    List<OrderItem> findByOrderId(long orderId);
    // 保存一个OrderItem信息
    long save(@Param("orderItem")OrderItem orderItem,
              @Param("orderId") long orderId);
}
