package com.wizz.demo.service;

import com.wizz.demo.model.Introduce;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IntroduceService {
    /**
     * 保存文件
     * @param file
     * @param param
     */
    void saveFile(MultipartFile file, Map<String, Object> param) throws Exception;

    /**
     * 获取所有数据
     * @return
     */
    List<Introduce> getList(Introduce introduce);

    /**
     * 查询单个
     * @param id
     * @return
     */
    Introduce getById(int id);

    /**
     * 修改
     * @param file
     */
    void update(MultipartFile file, Map param);

    /**
     * 根据id删除
     * @param id
     */
    void delete(int id);
}
