package com.neusoft.rebag;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_user")
public class User {
    /**
     * 指定主键为uuid增长策略
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String UserId;
    //对数据库中的字段进行约束
    @Column(name = "username",unique = true,nullable = false,length = 64)
    private String username;

    @Column(name = "password",nullable = false, length = 64)
    private String password;

    @Column(name = "email", length = 64)
    private String email;
}
