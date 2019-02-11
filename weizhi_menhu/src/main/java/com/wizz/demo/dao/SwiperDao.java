package com.wizz.demo.dao;

import com.wizz.demo.model.Swiper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwiperDao {
    @Insert("INSERT INTO swiper (rank,name,date) VALUES(#{rank}, #{name}, #{date})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(Swiper swiper);


    /**
     * 功能描述：查找全部
     * @return
     */
    @Select("SELECT * FROM swiper")
    @Results({
            @Result(column = "time",property = "time"),
            @Result(column = "id",property = "id"),
            @Result(column = "date",property = "date"),
            @Result(column = "rank",property = "rank"),
            //javaType = java.util.Date.class
    })
    List<Swiper> getAll();


    /**
     * 功能描述：根据id找对象
     * @param id
     * @return
     */

    @Select("SELECT * FROM swiper WHERE id = #{id}")
    @Results({
            @Result(column = "time",property = "time"),
            @Result(column = "id",property = "id"),
            @Result(column = "date",property = "date"),
            @Result(column = "rank",property = "rank"),
    })
    List<Swiper> findById(int id);


    /**
     * 功能描述：更新对象
     * @param swiper
     */
    @Update("UPDATE swiper SET rank=#{rank} WHERE id =#{id}")
    void update(Swiper swiper);



    /**
     * 功能描述：根据id删除某张照片在数据库的记录
     * @params id
     */
    @Delete("DELETE FROM swiper WHERE id =#{id}")
    void delete(int id);
}
