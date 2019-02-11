package com.wizz.demo.service;

import com.wizz.demo.model.WizzHistory;

import java.util.List;

public interface HistoryService {
    String getMainTitle(int id);
    String getBriefIntro(int id);
    int updateInfo(WizzHistory wizzHistory);
    int addInfo(WizzHistory wizzHistory);
    int deleteInfo(int id);
    List<WizzHistory> findAll();

}
