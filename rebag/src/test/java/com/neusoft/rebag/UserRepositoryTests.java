package com.neusoft.rebag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    /**
     * 新建用户测试
     */
    @Test
    public void save(){
        User user = new User();
        user.setUsername("李四");
        user.setPassword("777");
        user.setEmail("333@qq.com");
        userRepository.save(user);
    }
    /**
     *
     */
    @Test
    public void update(){
        User user = new User();
        user.setUserId("4028098175ff2b160175ff2b1c710000");
        user.setUsername("李四");
        user.setPassword("aaa123");
        user.setEmail("666@qq.com");
        userRepository.save(user);

    }
    /**
     * 根据id查询用户
     */
    @Test
    public void findById(){
        User user = new User();
        Optional<User> optional = userRepository.findById("4028098175ff2b160175ff2b1c710000");
        user = optional.get();
        System.out.println(user);

    }

    /**
     * 删除用户测试
     */
    @Test
    public void delete(){
      String Id = "4028098175ff38df0175ff38e4b20000";
        userRepository.deleteById(Id);
    }

    /**
     *查询所有用户测试
     */
    @Test
    public void findAll(){
        List<User> list = userRepository.findAll();
        for(User u:list){
            System.out.println(u);
        }
    }

}
