package neusoft.springbootsell.repository;
import neusoft.springbootsell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTests {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final String OPENID = "110110";
    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("小丸子");
        orderMaster.setBuyerPhone("139999999999");
        orderMaster.setBuyerAddress("江科大南门");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.9));
        OrderMaster result = orderMasterRepository.save(orderMaster);
//        System.out.println(master);
        Assert.assertNotNull(result);
    }
    @Test
    public void update(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("hahah");
        orderMaster.setBuyerPhone("1388888888");
        orderMaster.setBuyerAddress("江科大西门");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(3.0));
        OrderMaster save = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(orderMaster);
    }
    @Test
    public void deleteById(){
        orderMasterRepository.delete("111");
    }

    @Test
    public void findById(){
        OrderMaster master = orderMasterRepository.findOne("1234567");
        System.out.println(master);
        Assert.assertNotNull(master);
    }
    @Test
    public void findAll(){
        List<OrderMaster> list = new ArrayList<>();
       list = orderMasterRepository.findAll();
        for(OrderMaster o:list){
            System.out.println(o);
        }
    }

}