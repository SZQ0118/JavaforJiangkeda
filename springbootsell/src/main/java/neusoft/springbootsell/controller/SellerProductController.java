package neusoft.springbootsell.controller;

import neusoft.springbootsell.Utils.KeyUtil;
import neusoft.springbootsell.dataobject.ProductCategory;
import neusoft.springbootsell.dataobject.ProductInfo;
import neusoft.springbootsell.enums.ProductStatusEnum;
import neusoft.springbootsell.enums.ResultEnum;
import neusoft.springbootsell.exception.SellException;
import neusoft.springbootsell.form.ProductForm;
import neusoft.springbootsell.services.CategoryService;
import neusoft.springbootsell.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家商品控制
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "5") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("page",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            //修改
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询类目并且进行返回
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map
                             ){

        if(bindingResult.hasErrors()){
            //返回错误页面
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/product/index");
            return new ModelAndView("common/error");
        }

           ProductInfo productInfo = new ProductInfo();
       try {
           if(!StringUtils.isEmpty(form.getProductId())){
               //有productid  修改
               productInfo = productService.findOne(form.getProductId());

           }else {
               // 新增 生成一个id
               form.setProductId(KeyUtil.getUniqueKey());
           }
               BeanUtils.copyProperties(form,productInfo);
               productService.save(productInfo);
       }catch (SellException exception){
           map.put("msg",exception.getMessage());
           map.put("url","/seller/product/index");
           return new ModelAndView("common/error");
       }
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success");
    }
    @GetMapping("/up")
    public ModelAndView upStatus(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.onSale(productId);
            if(productInfo.getProductStatus()== ProductStatusEnum.DOWN.getCode()){
                productService.onSale(productId);
            }
        }
        map.put("url","/seller/product/list");
        return new ModelAndView("product/up");
    }

    @GetMapping("/off")
    public ModelAndView offStatus(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.offSale(productId);
            if(productInfo.getProductStatus()== ProductStatusEnum.UP.getCode()){
                productService.offSale(productId);
            }
        }
        map.put("url","/seller/product/list");
        return new ModelAndView("product/off");
    }
}
