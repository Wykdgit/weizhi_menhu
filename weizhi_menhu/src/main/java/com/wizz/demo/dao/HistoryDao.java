package com.wizz.demo.dao;

import com.wizz.demo.model.WizzHistory;

import java.util.List;

public interface HistoryDao {
    String getMainTitleById(int id);//查
    String getBriefIntroById(int id);
    int update(WizzHistory wizzHistory);//改
    int addHistory(WizzHistory wizzHistory);//增
    int deleteHistory(int id);
    List<WizzHistory> findAll();
}
