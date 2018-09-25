package com.hsshy.beam.modular.hello;

/**
 * @description: 测试类
 * @author: hs
 * @create: 2018-09-20 22:42:04
 **/
public class Hello<T> {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Hello hello(String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
}
