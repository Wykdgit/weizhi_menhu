package com.wizz.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {//谢安怡的user
    private int id;

    @NotBlank(message = "userName can't be null")
    @Size(min = 1,max = 20,message = "username should be between 1 and 20")
    private String name;

    @NotBlank(message = "pass word can't be null")
    @Size(min = 1,max=20,message = "password should be between 1 and 20")
    private String password;

    private Date createDate;
    private Power power;//权限

    public static enum Power{
        ROLE_USER,ROLE_ADMIN,ROLE_SUPER
    }
    public User(){
        this.createDate=new Date();
        this.power=Power.ROLE_USER;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }
}
