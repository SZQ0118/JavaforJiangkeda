package neusoft.springbootsell.services.Impl;

import neusoft.springbootsell.dataobject.OrderDetail;
import neusoft.springbootsell.repository.OrderDetailRepository;
import neusoft.springbootsell.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository repository;
    @Override
    public List<OrderDetail> findOneByOrderId(String orderId) {
        return repository.findByOrderIdIn(orderId);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return null;
    }
}
