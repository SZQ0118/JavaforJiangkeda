package com.neusoft.rebag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckyMoneyController {
    @Autowired
   private LuckMoneyRepository luckMoney;

    /**
     * 获取红包列表
     * @return
     */
    @RequestMapping("/list")
    public List<LuckyMoney> list(){
       return luckMoney.findAll();
    }

    /**
     * 发红包
     * @param producer
     * @param money
     * @return
     */
    @PostMapping("/post")
    public LuckyMoney postRedBag(@RequestParam(value = "pro",required = true) String producer, @RequestParam(value = "mon",required = true)BigDecimal money){
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        luckyMoney.setMoney(money);
        return luckMoney.save(luckyMoney);
    }

    /**
     * 根据Id查询红包
     * @param Id
     * @return
     */
    @GetMapping("/find/{id}")
    public LuckyMoney findById(@PathVariable("id") Integer Id){
//        方法一
//        Optional<LuckyMoney> optional = luckMoney.findById(Id);
//        if(optional.isPresent()){
//            LuckyMoney luckyMoney = optional.get();
//            return luckyMoney;
//        }
//        return null;
//
        //方法2
        return luckMoney.findById(Id).orElse(null);
    }

    /**
     * 收红包
     * @param Id
     * @param consumer
     * @return
     */
    @PutMapping("/put/{id}")
    public LuckyMoney put(@PathVariable("id") Integer Id,@RequestParam("consumer") String consumer){
        Optional<LuckyMoney> optional = luckMoney.findById(Id);
        if(optional.isPresent()){
            LuckyMoney luckyMoney = optional.get();
            luckyMoney.setConsumer(consumer);
            return luckMoney.save(luckyMoney);
        }
       return null;
    }
}

