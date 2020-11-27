package neusoft.springbootsell.controller;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import neusoft.springbootsell.Utils.vo.ProductInfoVO;
import neusoft.springbootsell.Utils.vo.ProductVO;
import neusoft.springbootsell.Utils.vo.ResultVo;
import neusoft.springbootsell.dataobject.ProductCategory;
import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.services.Impl.CategoryServiceImpl;
import neusoft.springbootsell.services.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;
    @GetMapping("/list")
    private ResultVo list(){
        //查询在架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目
        List<Integer> productCategoryList = new ArrayList<>();

        for(ProductInfo p:productInfoList){
            productCategoryList.add(p.getCategoryType());
        }
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(productCategoryList);
        //json语句拼装
        //第二,三层类目信息拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategories){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
          for(ProductInfo productInfo:productInfoList)//遍历加架商品
          {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVOList.add(productInfoVO);
                }
          }
          productVO.setProductInfoVOList(productInfoVOList);
          productVOList.add(productVO);
        }
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setDate(productVOList);
        return resultVo;
    }

}
