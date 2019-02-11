package com.wizz.demo.dao;

import com.wizz.demo.model.User;
import com.wizz.demo.model.User.Power;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//注解形式的dao实现
public interface UserDao {
    @Select("select * from user where id=#{id}")
    public User getUserById(@Param("id") int id);
    @Select("select * from user where name=#{name}")
    public User getUserByName(@Param("name") String name);
    @Select("select * from user where power=#{power}")
    public List<User> getUserByPower(@Param("power") Power power);

    @Insert("insert into user(id,name,password,power,create_date) "
            + "values(null,#{name},#{password},#{power},#{createDate,jdbcType=DATE})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(User c);

    @Delete("delete from user where name=#{name}")
    public void deleteUserByName(@Param("name")String name);

    @Update("update user set name=#{name}, password=#{password}, power=#{power} "
            + "where id=#{id}")
    public void update(User user);

}
