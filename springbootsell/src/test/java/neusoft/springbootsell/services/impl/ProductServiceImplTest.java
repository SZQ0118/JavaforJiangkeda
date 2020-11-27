package neusoft.springbootsell.services.impl;

import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.services.Impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest{
    @Autowired
    ProductServiceImpl productService;
    @Test
    public void findOne(){
        ProductInfo productInfo = productService.findOne("123");
//        System.out.println(productInfo);
        Assert.assertEquals("123",productInfo.getProductId());
    }

    /**
     * 分页查询所有数据
     */
    @Test
    public void findAll(){
        int pageNum  = 1;
        int pageSize =10;
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize);
        Page<ProductInfo> all = productService.findAll(pageRequest);
        System.out.println(all);
        Assert.assertNotNull(all);
    }
    /**
     * 查询所有在架商品
     */
    @Test
    public void findUpAll(){
        List<ProductInfo> upAll = productService.findUpAll();
        for(ProductInfo info:upAll){
            System.out.println(info);
        }
        Assert.assertNotNull(upAll);
    }
    /**
     * 新增商品
     */
    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111");
        productInfo.setProductName("汉堡包");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("美味又好吃");
        productInfo.setProductIcon("www.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo save = productService.save(productInfo);
        System.out.println(save);
        Assert.assertNotNull(save);
    }
    /**
     * 上架商品
     */
    @Test
    public void increaseStock(){
        productService.increaseStock("121221");
    }

    /**
     * 下架商品
     */
    @Test
    public void decreaseStock(){
        productService.decreaseStock("111");
    }
    /**
     * 加库存
     */

    /**
     * 减库存
     */

}
