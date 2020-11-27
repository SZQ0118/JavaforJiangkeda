package neusoft.springbootsell.repository;

import neusoft.springbootsell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("123");
        orderDetail.setProductIcon("www.");
        orderDetail.setProductName("胶水");
        orderDetail.setProductPrice(new BigDecimal(23));
        orderDetail.setProductQuantity(30);
        OrderDetail save = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(save);
    }
    @Test
    public void deleteById(){
        orderDetailRepository.delete("1");
    }

    @Test
    public void upate(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("1");
    }
    @Test
    public void findAll()
    {
        List<OrderDetail> list = orderDetailRepository.findAll();
        Assert.assertNotEquals(0,list.size());
    }

}
