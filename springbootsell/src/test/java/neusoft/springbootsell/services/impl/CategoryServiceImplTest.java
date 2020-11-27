package neusoft.springbootsell.services.impl;

import neusoft.springbootsell.dataobject.ProductCategory;
import neusoft.springbootsell.services.CategoryService;
import neusoft.springbootsell.services.Impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest{

    @Autowired
    CategoryServiceImpl service;
    @Test
    public void findOne(){
        ProductCategory serviceOne = service.findOne(3);
        System.out.println(serviceOne);
        Assert.assertEquals(new Integer(3),serviceOne.getCategoryId());
    }
    @Test
    public void findAll(){
        List<ProductCategory> list = service.findAll();
        for(ProductCategory p:list){
            System.out.println(p);
        }
        Assert.assertNotEquals(0,list.size());
    }
    @Test
    public void findByCategoryTypeIn(){
//        List<Integer> Id = Arrays.asList(1,2);
        List<ProductCategory> list = service.findByCategoryTypeIn(Arrays.asList(2,3));
        for(ProductCategory p:list){
            System.out.println(p);
        }
    }
    @Test
    public void save(){
        ProductCategory productCategory = new ProductCategory("炸鸡",3);
        ProductCategory save = service.save(productCategory);
        Assert.assertNotNull(save);
    }
}
