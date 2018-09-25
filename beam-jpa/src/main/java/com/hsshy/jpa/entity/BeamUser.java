package com.hsshy.jpa.entity;

import javax.persistence.*;

@Table(name = "beam_user")
@Entity
public class BeamUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userName;//姓名
    
    @Column
    private Integer age;//年龄

    private Integer sex;//年龄

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}