package neusoft.springbootsell.services.impl;

import neusoft.springbootsell.dataobject.OrderMaster;
import neusoft.springbootsell.services.Impl.MasterServiceImpl;
import neusoft.springbootsell.services.MasterService;
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
public class MasterServiceImplTest {
    @Autowired
    MasterServiceImpl masterService;
    @Test
    public void findOne(){
        OrderMaster master = masterService.findOne("123457");
        Assert.assertNotNull(master);
        System.out.println(master);
    }

    @Test
    public void save(){
        OrderMaster master = new OrderMaster();
        master.setOrderId("123");
        master.setBuyerName("小小");
        master.setBuyerPhone("999999");
        master.setBuyerAddress("家乡");
        master.setBuyerOpenid("1010");
        master.setOrderAmount(new BigDecimal(30));
        master.setOrderStatus(0);
        master.setPayStatus(0);
        OrderMaster master1 = masterService.save(master);
        System.out.println(master1);
        Assert.assertNotNull(master1);
    }
}
