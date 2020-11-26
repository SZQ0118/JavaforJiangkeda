package com.neusoft.rebag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id")String userID){
        Optional<User> optional = userRepository.findById(userID);
        //假设获取不到前面的就会主动调用
        //对象::new 创建对象的简写   1.8以上可以使用
        return optional.orElseGet(User::new);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String userID){
        userRepository.deleteById(userID);
    }
    @PostMapping({"/{id}"})
    public User update(@PathVariable("id")String usrId,@RequestBody User user){
        user.setUserId(usrId);
        return userRepository.save(user);
    }

//    public List<User> list(){
//        return userRepository.findAll();
//    }
    //分页参数 页数和每页有多少个

    /**
     *分页查询
     * @param pageNum  页的开始数,默认是从0开始
     * @param pageSize  每页第几个
     * @return
     */
    @GetMapping("/list")
     public Page<User> pageQuery(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){

        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return userRepository.findAll(pageRequest);
     }
}
