package com.wizz.demo.dao;

import com.wizz.demo.model.SysFile;

import java.util.List;

public interface SysFileDao {
    /**
     * 添加
     * @param sysFile
     */
    void save(SysFile sysFile);

    /**
     * 删除
     * @param id
     */
    void del(int id);

    /**
     * 修改
     * @param sysFile
     */
    void update(SysFile sysFile);

    /**
     * 查询所有
     * @param sysFile
     * @return
     */
    List<SysFile> getList(SysFile sysFile);

    /**
     * 根据id查询具体
     * @param id
     * @return
     */
    SysFile get(int id);
}
