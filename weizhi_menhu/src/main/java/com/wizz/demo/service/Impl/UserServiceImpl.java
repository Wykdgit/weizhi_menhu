package com.wizz.demo.service.Impl;

import com.wizz.demo.dao.UserDao;
import com.wizz.demo.model.User;
import com.wizz.demo.model.User.Power;
import com.wizz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public List<User> getUserByPower(Power power) {
        return userDao.getUserByPower(power);
    }

    @Override
    public boolean insert(User c) {
        User user = userDao.getUserByName(c.getName());
        if(user ==null){
            c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
            userDao.insert(c);
            return true;
        }
        else{
            System.out.println("用户"+c.getName()+"已经注册过了");
            return false;
        }
    }

    @Override
    public void deleteUserByName(String name) {
        userDao.deleteUserByName(name);

    }

    @Override
    public void update(User c) {
        c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
        userDao.update(c);

    }
}
