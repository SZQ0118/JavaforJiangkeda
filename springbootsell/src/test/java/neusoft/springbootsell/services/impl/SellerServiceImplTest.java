package neusoft.springbootsell.services.impl;

import net.minidev.json.JSONUtil;
import neusoft.springbootsell.dataobject.SellerInfo;
import neusoft.springbootsell.services.Impl.SellerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    @Autowired
    SellerServiceImpl sellerService;
    @Test
    public void Test(){
        Boolean adnim = sellerService.getByIdAndPassword("adnim", "123");
        System.out.println(adnim);

    }
}
