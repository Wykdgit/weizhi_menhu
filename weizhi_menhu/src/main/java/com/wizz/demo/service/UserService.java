package com.wizz.demo.service;

import com.wizz.demo.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(int id);


    public User getUserByName(String name);


    public List<User> getUserByPower(User.Power power);


    public boolean insert(User c) ;


    public void deleteUserByName(String name);


    public void update(User user);
}
