package neusoft.springbootsell.services;

import neusoft.springbootsell.dataobject.OrderDetail;
import neusoft.springbootsell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MasterService {

    //查找一位买家信息
    OrderMaster findOne(String orderId);

    //查找所有买家信息
    Page<OrderMaster> findAll(Pageable pageable);

    //新增买家信息
    OrderMaster save(OrderMaster orderMaster);


    //取消订单
    OrderMaster cancelOrder(String OrderId);


}
