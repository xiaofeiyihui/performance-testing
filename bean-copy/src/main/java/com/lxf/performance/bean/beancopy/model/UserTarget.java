package com.lxf.performance.bean.beancopy.model;

import java.util.Date;

/**
 * @Description:
 * @Author: xfyh
 * @Date: 2020/4/21 19:37
 */
public class UserTarget {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Date loginTime;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public UserTarget() {
    }

    public UserTarget(Integer id, String name, Integer age, String sex, Date loginTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.loginTime = loginTime;
    }
}
