package neusoft.springbootsell.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import neusoft.springbootsell.dataobject.SellerInfo;
import neusoft.springbootsell.enums.ResultEnum;
import neusoft.springbootsell.services.Impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerServiceImpl sellerService;

    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map) {
        return new ModelAndView("seller/index");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid String Id,@Valid String password, Map<String, Object> map) {

        Boolean result = sellerService.getByIdAndPassword(Id, password);
        if(result==true){
            map.put("msg","登陆成功");
            map.put("url","/seller/product/list");
            return new ModelAndView("common/success");
        }else {
            map.put("msg",ResultEnum.SELLER_ID_ERROR.getMessage());
            map.put("url","/seller/index");
            return new ModelAndView("common/error");
        }
    }
}