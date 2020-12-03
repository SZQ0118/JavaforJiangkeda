package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.OrderMaster;
import neusoft.springbootsell.enums.OrderStatusEnum;
import neusoft.springbootsell.enums.ProductStatusEnum;
import neusoft.springbootsell.repository.OrderMasterRepository;
import neusoft.springbootsell.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MasterServiceImpl implements MasterService {
    @Autowired
    OrderMasterRepository repository;
    @Override
    public OrderMaster findOne(String orderId) {

        return repository.findOne(orderId);
    }

    @Override
    public Page<OrderMaster> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = repository.findAll(pageable);
        return orderMasterPage;
    }


    @Override
    public OrderMaster save(OrderMaster orderMaster) {
        return repository.save(orderMaster);
    }

    @Override
    public OrderMaster cancelOrder(String OrderId) {
        OrderMaster master = repository.findOne(OrderId);
        master.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return repository.save(master);
    }
}
