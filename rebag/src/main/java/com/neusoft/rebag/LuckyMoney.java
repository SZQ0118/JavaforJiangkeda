package com.neusoft.rebag;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.BitSet;

/**
 * LuckyMoney 是与数据库表中字段一一对应的实体类
 * JavaBean格式
 */
@Entity
@Data
public class LuckyMoney {
    //@Entity 指定该类是实体类、@Id 代表主键 @GeneratedValue代表自增长
    @Id
    @GeneratedValue
    private Integer id;
    // 红包金额
    private BigDecimal money;
    // 发红包的人
    private String producer;
    // 收红包的人
    private String consumer;
    public LuckyMoney(){

    }

}

