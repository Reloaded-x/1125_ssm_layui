package com.sy.model;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:23
 * @Version 1.0
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}
