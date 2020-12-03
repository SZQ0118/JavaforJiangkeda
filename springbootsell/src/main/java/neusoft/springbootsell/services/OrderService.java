package neusoft.springbootsell.services;

import neusoft.springbootsell.dataobject.OrderDetail;

import java.util.List;

public interface OrderService {
    //根据订单Id查询订单信息
    List<OrderDetail> findOneByOrderId(String orderId);
    //新增订单
    OrderDetail save(OrderDetail orderDetail);
}
