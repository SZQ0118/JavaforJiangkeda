package neusoft.springbootsell.controller;

import neusoft.springbootsell.dataobject.OrderDetail;
import neusoft.springbootsell.dataobject.OrderMaster;
import neusoft.springbootsell.enums.ResultEnum;
import neusoft.springbootsell.services.Impl.MasterServiceImpl;
import neusoft.springbootsell.services.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private MasterServiceImpl masterService;
    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "5") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<OrderMaster> orderMasterPage = masterService.findAll(pageRequest);
        map.put("orderMasterPage",orderMasterPage);
        map.put("page",page);
        map.put("size",size);

        return new ModelAndView("order/list");
    }

    @GetMapping("/index")
    public ModelAndView detail(@RequestParam("orderId") String orderId,Map<String,Object>map){
        OrderMaster orderMaster = masterService.findOne(orderId);
        List<OrderDetail> orderDetailList = orderService.findOneByOrderId(orderId);
        if(orderDetailList.size()==0){
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error");
        }else {
            map.put("orderMaster",orderMaster);
            map.put("orderDetailList",orderDetailList);
            return new ModelAndView("/order/index",map);
        }
    }

    @GetMapping("/cancel")
    public ModelAndView cancelOrder(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderMaster master = masterService.cancelOrder(orderId);
        if(master!=null){
            map.put("url","/seller/order/list");
            return new ModelAndView("/order/cancel",map);
        }else {

            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error");
        }
    }

//    @PostMapping("/save")
//    public ModelAndView createOrder(Map<String,Object> map){
//
//
//        map.put("url","/seller/order/list");
//        return new ModelAndView("common/success");
//    }
}
