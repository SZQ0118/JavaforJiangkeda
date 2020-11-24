package com.neusoft.rebag;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Name;
    private String sex;
    private Integer age;
    public User() {
    }
}
