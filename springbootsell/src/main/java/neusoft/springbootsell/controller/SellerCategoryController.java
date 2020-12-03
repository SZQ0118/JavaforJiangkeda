package neusoft.springbootsell.controller;

import neusoft.springbootsell.dataobject.ProductCategory;
import neusoft.springbootsell.enums.ResultEnum;
import neusoft.springbootsell.exception.SellException;
import neusoft.springbootsell.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> list = categoryService.findAll();
        // 将list添加到map中
        map.put("categoryList", list);
        return new ModelAndView("category/list", map);
    }
    @GetMapping("/index")
    public ModelAndView index(Map<String,Object> map){
       return new ModelAndView("category/index");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductCategory productCategory , BindingResult bindingResult, Map<String,Object> map){
        List<Integer> categoryId = new ArrayList<>();
        List<ProductCategory> categoryList = categoryService.findAll();
        for(ProductCategory category:categoryList){
            if(category.getCategoryName().equals(productCategory.getCategoryName())){
                map.put("msg", ResultEnum.CATEGORY_NAME_ERROR.getMessage());
                map.put("url","/seller/category/index");
                return new ModelAndView("common/error");
            }
        }

        if(bindingResult.hasErrors()){
            //返回错误页面
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/category/index");
            return new ModelAndView("common/error");
        }
        categoryId.add(productCategory.getCategoryType());
        try {
            //判断类目编号是否存在
            if(categoryService.findByCategoryTypeIn(categoryId).isEmpty())
            {
                categoryService.save(productCategory);
            }else {
                map.put("msg", ResultEnum.CATEGORY_TYPE_ERROR.getMessage());
                map.put("url","/seller/category/index");
                return new ModelAndView("common/error");
            }
        }catch (SellException exception){
            map.put("msg",exception.getMessage());
            map.put("url","/seller/category/index");
            return new ModelAndView("common/error");
        }
        map.put("url","/seller/category/list");
        return new ModelAndView("common/success");
    }

}



