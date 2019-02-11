package com.wizz.demo.dao;

import com.wizz.demo.model.Introduce;

import java.util.List;

public interface IntroduceDao {
    /**
     * 添加
     * @param introduce
     */
    void save(Introduce introduce);

    /**
     * 删除
     * @param id
     */
    void del(int id);

    /**
     * 修改
     * @param introduce
     */
    void update(Introduce introduce);

    /**
     * 查询所有
     * @param introduce
     * @return
     */
    List<Introduce> getList(Introduce introduce);

    /**
     * 根据id查询具体
     * @param id
     * @return
     */
    Introduce get(int id);
}
